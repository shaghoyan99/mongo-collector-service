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
public class SupportInfo {

    @Field("warranty_months")
    private int warrantyMonths;

    @Field("email")
    private String email;

    @Field("address")
    private Address address;
}
