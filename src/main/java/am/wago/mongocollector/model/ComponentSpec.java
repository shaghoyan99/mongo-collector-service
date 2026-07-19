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
public class ComponentSpec {

    @Field("model")
    private String model;

    @Field("socket")
    private String socket;

    @Field("tdp_watts")
    private int tdpWatts;

    @Field("performance")
    private PerformanceSpec performance;
}
