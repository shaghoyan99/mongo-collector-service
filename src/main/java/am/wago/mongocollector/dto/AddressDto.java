package am.wago.mongocollector.dto;

public record AddressDto(
        String street,
        String city,
        String country,
        String zipCode
) {}
