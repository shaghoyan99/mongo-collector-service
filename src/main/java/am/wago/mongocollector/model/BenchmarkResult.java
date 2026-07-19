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
public class BenchmarkResult {

    @Field("single_core_score")
    private int singleCoreScore;

    @Field("multi_core_score")
    private int multiCoreScore;

    @Field("test_tool")
    private String testTool;
}
