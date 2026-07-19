package am.wago.mongocollector.service;

import am.wago.mongocollector.entity.ComponentEvent;
import am.wago.mongocollector.mapper.ComputerComponentMapper;
import am.wago.mongocollector.model.ComputerComponent;
import am.wago.mongocollector.repository.ComponentEventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostgresSyncService {

    private final ComponentEventRepository eventRepository;
    private final ComputerComponentMapper mapper;

    @Transactional
    public void sync(ComputerComponent component) {
        if (component == null || component.getId() == null) {
            return;
        }

        if (eventRepository.findByMongoId(component.getId()).isPresent()) {
            log.debug("Already synced, mongoId={}", component.getId());
            return;
        }

        ComponentEvent event = mapper.toEntity(component);
        eventRepository.save(event);
        log.info("Synced to PostgreSQL mongoId={} category={} model={}",
                component.getId(),
                component.getCategory(),
                component.getSpecifications() != null ? component.getSpecifications().getModel() : null);
    }
}
