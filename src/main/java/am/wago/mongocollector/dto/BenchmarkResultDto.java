package am.wago.mongocollector.dto;

public record BenchmarkResultDto(
        int singleCoreScore,
        int multiCoreScore,
        String testTool
) {}
