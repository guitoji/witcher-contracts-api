package com.guitoji.witcher_contracts.dto.request;

import com.guitoji.witcher_contracts.model.enums.MonsterClassification;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MonsterDTO(
        @NotBlank(message = "name is mandatory")
        String creatureName,
        @NotNull(message = "classification is mandatory")
        MonsterClassification classification
) {
}
