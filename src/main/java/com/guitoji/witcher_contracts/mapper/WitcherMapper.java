package com.guitoji.witcher_contracts.mapper;

import com.guitoji.witcher_contracts.dto.request.WitcherDTO;
import com.guitoji.witcher_contracts.dto.response.ResultWitcherDTO;
import com.guitoji.witcher_contracts.model.Witcher;
import com.guitoji.witcher_contracts.model.WitcherSchool;
import com.guitoji.witcher_contracts.service.WitcherSchoolService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR, uses = {WitcherSchoolMapper.class})
public abstract class WitcherMapper {

    @Autowired
    private WitcherSchoolService witcherSchoolService;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "school", source = "idSchool", qualifiedByName = "getWitcherSchool")
    public abstract Witcher toEntity(WitcherDTO dto);

    @Mapping(target = "resultWitcherSchoolDTO", source = "school")
    public abstract ResultWitcherDTO toDTO(Witcher witcher);

    @Named("getWitcherSchool")
    protected WitcherSchool getWitcherSchool(UUID idSchool) {
        return witcherSchoolService.findByIdReturningWitcherSchool(idSchool);
    }
}
