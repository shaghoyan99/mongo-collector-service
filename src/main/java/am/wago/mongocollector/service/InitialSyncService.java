package am.wago.mongocollector.service;

import am.wago.mongocollector.model.ComputerComponent;
import am.wago.mongocollector.repository.ComputerComponentRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class InitialSyncService {

    private final ComputerComponentRepository mongoRepository;
    private final PostgresSyncService postgresSyncService;

    @PostConstruct
    public void syncExisting() {
        List<ComputerComponent> existing = mongoRepository.findAll();

        if (existing.isEmpty()) {
            log.info("Initial sync: no documents found in MongoDB, skipping.");
            return;
        }

        log.info("Initial sync: found {} documents in MongoDB, syncing to PostgreSQL...", existing.size());

        existing.forEach(postgresSyncService::sync);

        log.info("Initial sync complete: {} documents synced.", existing.size());
    }
}
