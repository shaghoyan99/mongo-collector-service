package am.wago.mongocollector.dto;

public record SupportInfoDto(
        int warrantyMonths,
        String email,
        AddressDto address
) {}
