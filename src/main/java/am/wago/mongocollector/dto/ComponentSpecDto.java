package am.wago.mongocollector.dto;

public record ComponentSpecDto(
        String model,
        String socket,
        int tdpWatts,
        PerformanceSpecDto performance
) {}
