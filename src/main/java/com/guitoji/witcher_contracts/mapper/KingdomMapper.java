package com.guitoji.witcher_contracts.mapper;

import com.guitoji.witcher_contracts.dto.request.KingdomDTO;
import com.guitoji.witcher_contracts.model.Kingdom;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface KingdomMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "contracts", ignore = true)
    public Kingdom toEntity(KingdomDTO dto);
}
