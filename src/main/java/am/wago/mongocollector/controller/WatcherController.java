package am.wago.mongocollector.controller;

import am.wago.mongocollector.dto.ComputerComponentDto;
import am.wago.mongocollector.mapper.ComputerComponentMapper;
import am.wago.mongocollector.repository.ComputerComponentRepository;
import am.wago.mongocollector.repository.ComponentEventRepository;
import am.wago.mongocollector.service.MongoWatcherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/watcher")
@RequiredArgsConstructor
public class WatcherController {

    private final MongoWatcherService watcherService;
    private final ComputerComponentRepository mongoRepository;
    private final ComponentEventRepository postgresRepository;
    private final ComputerComponentMapper mapper;

    @GetMapping("/status")
    public ResponseEntity<Map<String, Object>> status() {
        return ResponseEntity.ok(Map.of(
                "running", watcherService.isRunning(),
                "watchCollection", watcherService.getWatchCollection(),
                "mongoCount", mongoRepository.count(),
                "postgresCount", postgresRepository.count()
        ));
    }

    @GetMapping("/components")
    public ResponseEntity<List<ComputerComponentDto>> listComponents() {
        List<ComputerComponentDto> dtos = mongoRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
        return ResponseEntity.ok(dtos);
    }
}
