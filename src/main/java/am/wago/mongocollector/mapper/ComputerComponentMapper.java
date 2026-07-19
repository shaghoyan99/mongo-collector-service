package am.wago.mongocollector.mapper;

import am.wago.mongocollector.dto.ComputerComponentDto;
import am.wago.mongocollector.entity.ComponentEvent;
import am.wago.mongocollector.model.ComputerComponent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ComputerComponentMapper {

    ComputerComponentDto toDto(ComputerComponent model);

    @Mapping(source = "id",                                              target = "mongoId")
    @Mapping(source = "manufacturer.name",                               target = "manufacturerName")
    @Mapping(source = "manufacturer.country",                            target = "manufacturerCountry")
    @Mapping(source = "manufacturer.support.warrantyMonths",             target = "warrantyMonths")
    @Mapping(source = "manufacturer.support.email",                      target = "supportEmail")
    @Mapping(source = "manufacturer.support.address.city",               target = "addressCity")
    @Mapping(source = "manufacturer.support.address.country",            target = "addressCountry")
    @Mapping(source = "specifications.model",                            target = "model")
    @Mapping(source = "specifications.socket",                           target = "socket")
    @Mapping(source = "specifications.tdpWatts",                         target = "tdpWatts")
    @Mapping(source = "specifications.performance.clockSpeedGhz",        target = "clockSpeedGhz")
    @Mapping(source = "specifications.performance.coreCount",            target = "coreCount")
    @Mapping(source = "specifications.performance.memoryType",           target = "memoryType")
    @Mapping(source = "specifications.performance.benchmark.singleCoreScore", target = "singleCoreScore")
    @Mapping(source = "specifications.performance.benchmark.multiCoreScore",  target = "multiCoreScore")
    @Mapping(source = "specifications.performance.benchmark.testTool",        target = "testTool")
    @Mapping(source = "createdAt",                                       target = "mongoCreatedAt")
    @Mapping(target = "id",                                              ignore = true)
    @Mapping(target = "syncedAt",                                        expression = "java(java.time.Instant.now())")
    ComponentEvent toEntity(ComputerComponent model);
}
