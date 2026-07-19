package am.wago.mongocollector.dto;

public record ManufacturerDto(
        String name,
        String country,
        SupportInfoDto support
) {}
