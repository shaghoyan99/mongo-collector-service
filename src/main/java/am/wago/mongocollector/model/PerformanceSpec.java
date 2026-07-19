package am.wago.mongocollector.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PerformanceSpec {

    @Field("clock_speed_ghz")
    private double clockSpeedGhz;

    @Field("core_count")
    private int coreCount;

    @Field("memory_type")
    private String memoryType;

    @Field("benchmark")
    private BenchmarkResult benchmark;
}
