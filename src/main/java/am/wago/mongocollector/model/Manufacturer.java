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
public class Manufacturer {

    @Field("name")
    private String name;

    @Field("country")
    private String country;

    @Field("support")
    private SupportInfo support;
}
