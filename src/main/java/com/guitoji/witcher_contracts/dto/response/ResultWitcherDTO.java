package com.guitoji.witcher_contracts.dto.response;

import com.guitoji.witcher_contracts.model.enums.WitcherMastery;

import java.util.UUID;

public record ResultWitcherDTO(
        UUID id,
        String name,
        WitcherMastery mastery,
        ResultWitcherSchoolDTO resultWitcherSchoolDTO
) {
}
