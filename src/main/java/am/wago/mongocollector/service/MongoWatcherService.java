package am.wago.mongocollector.service;

import am.wago.mongocollector.model.ComputerComponent;
import com.mongodb.client.model.changestream.FullDocument;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.messaging.ChangeStreamRequest;
import org.springframework.data.mongodb.core.messaging.DefaultMessageListenerContainer;
import org.springframework.data.mongodb.core.messaging.Message;
import org.springframework.data.mongodb.core.messaging.MessageListenerContainer;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MongoWatcherService {

    private final MongoTemplate mongoTemplate;
    private final PostgresSyncService postgresSyncService;

    @Value("${app.mongo.watch.collection}")
    private String watchCollection;

    private MessageListenerContainer listenerContainer;

    @EventListener(ApplicationReadyEvent.class)
    public void startWatching() {
        listenerContainer = new DefaultMessageListenerContainer(mongoTemplate);
        listenerContainer.start();

        ChangeStreamRequest<ComputerComponent> request = ChangeStreamRequest.<ComputerComponent>builder(this::onChange)
                .collection(watchCollection)
                .fullDocumentLookup(FullDocument.UPDATE_LOOKUP)
                .build();

        listenerContainer.register(request, ComputerComponent.class);
        log.info("MongoWatcherService started, watching collection: {}", watchCollection);
    }

    /**
     * message.getBody() is the change event already converted to a typed POJO —
     * no BSON parsing anywhere.
     */
    private void onChange(Message<?, ComputerComponent> message) {
        ComputerComponent component = message.getBody();
        if (component == null) {
            log.info("Change event with empty body on collection {}", watchCollection);
            return;
        }
        log.info("Change event collection={} _id={} category={}",
                watchCollection, component.getId(), component.getCategory());
        postgresSyncService.sync(component);
    }

    @PreDestroy
    public void stopWatching() {
        if (listenerContainer != null) {
            listenerContainer.stop();
        }
        log.info("MongoWatcherService stopped");
    }

    public boolean isRunning() {
        return listenerContainer != null && listenerContainer.isRunning();
    }

    public String getWatchCollection() {
        return watchCollection;
    }
}
