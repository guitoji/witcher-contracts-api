package com.guitoji.witcher_contracts.mapper;

import com.guitoji.witcher_contracts.dto.request.MonsterDTO;
import com.guitoji.witcher_contracts.dto.response.ResultMonsterDTO;
import com.guitoji.witcher_contracts.model.Monster;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface MonsterMapper {

    @Mapping(target = "id", ignore = true)
    public Monster toEntity(MonsterDTO dto);

    public ResultMonsterDTO toDTO(Monster monster);
}
