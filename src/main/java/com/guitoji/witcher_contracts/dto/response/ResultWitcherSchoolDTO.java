package com.guitoji.witcher_contracts.dto.response;

import com.guitoji.witcher_contracts.model.Witcher;

import java.util.List;
import java.util.UUID;

public record ResultWitcherSchoolDTO(
        UUID id,
        String name,
        List<Witcher> witchers
) {
}
