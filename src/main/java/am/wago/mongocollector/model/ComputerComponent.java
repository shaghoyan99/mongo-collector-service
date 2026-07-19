package am.wago.mongocollector.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "computer_components")
public class ComputerComponent {

    @Id
    private String id;

    @Indexed
    @Field("category")
    private String category;

    @Field("price")
    private BigDecimal price;

    @Field("currency")
    private String currency;

    @Field("manufacturer")
    private Manufacturer manufacturer;

    @Field("specifications")
    private ComponentSpec specifications;

    @CreatedDate
    @Field("created_at")
    private Instant createdAt;

    @LastModifiedDate
    @Field("updated_at")
    private Instant updatedAt;
}
