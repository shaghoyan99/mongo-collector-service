package am.wago.mongocollector.dto;

import java.math.BigDecimal;
import java.time.Instant;

public record ComputerComponentDto(
        String id,
        String category,
        BigDecimal price,
        String currency,
        ManufacturerDto manufacturer,
        ComponentSpecDto specifications,
        Instant createdAt,
        Instant updatedAt
) {}
