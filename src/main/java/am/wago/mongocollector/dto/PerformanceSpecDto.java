package am.wago.mongocollector.dto;

public record PerformanceSpecDto(
        double clockSpeedGhz,
        int coreCount,
        String memoryType,
        BenchmarkResultDto benchmark
) {}
