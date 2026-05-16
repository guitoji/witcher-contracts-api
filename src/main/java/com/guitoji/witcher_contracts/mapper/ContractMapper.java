package com.guitoji.witcher_contracts.mapper;

import com.guitoji.witcher_contracts.dto.request.ContractDTO;
import com.guitoji.witcher_contracts.model.Contract;
import com.guitoji.witcher_contracts.model.Kingdom;
import com.guitoji.witcher_contracts.model.Monster;
import com.guitoji.witcher_contracts.service.KingdomService;
import com.guitoji.witcher_contracts.service.MonsterService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR,
        uses = {KingdomMapper.class, MonsterMapper.class})
public abstract class ContractMapper {

    @Autowired
    private KingdomService kingdomService;

    @Autowired
    private MonsterService monsterService;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "kingdom", source = "idKingdom", qualifiedByName = "getKingdom")
    @Mapping(target = "monster", source = "idMonster", qualifiedByName = "getMonster")
    public abstract Contract toEntity(ContractDTO dto);

    @Named("getKingdom")
    protected Kingdom getKingdom(UUID idKingdom) {
        return kingdomService.getKingdomToContract(idKingdom);
    }

    @Named("getMonster")
    protected Monster getMonster(UUID idMonster) {
        return monsterService.getMonsterToContract(idMonster);
    }
}
