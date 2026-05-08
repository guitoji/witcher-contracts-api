package com.guitoji.witcher_contracts.mapper;

import com.guitoji.witcher_contracts.dto.request.WitcherSchoolDTO;
import com.guitoji.witcher_contracts.dto.response.ResultWitcherSchoolByName;
import com.guitoji.witcher_contracts.dto.response.ResultWitcherSchoolDTO;
import com.guitoji.witcher_contracts.model.WitcherSchool;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface WitcherSchoolMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "witchers", ignore = true)
    public WitcherSchool toEntity(WitcherSchoolDTO dto);

    @Mapping(target = "witchers", ignore = true)
    public ResultWitcherSchoolDTO toDTO(WitcherSchool witcherSchool);

    public ResultWitcherSchoolByName getByNameDTO(WitcherSchool witcherSchool);
}
