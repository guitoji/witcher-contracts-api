package com.guitoji.witcher_contracts.dto.response;

import com.guitoji.witcher_contracts.model.enums.MonsterClassification;

import java.util.UUID;

public record ResultMonsterDTO(
        UUID id,
        String creatureName,
        MonsterClassification classification
) {
}
