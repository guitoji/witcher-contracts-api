package com.guitoji.witcher_contracts.dto.request;

import com.guitoji.witcher_contracts.model.enums.WitcherMastery;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record WitcherDTO(
        @NotBlank(message = "name is mandatory")
        String name,
        @NotNull(message = "mastery is mandatory")
        WitcherMastery mastery,
        @NotNull(message = "witcher school is mandatory")
        UUID idSchool
) {
}
