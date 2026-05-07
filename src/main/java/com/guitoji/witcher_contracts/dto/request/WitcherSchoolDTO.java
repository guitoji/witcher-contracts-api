package com.guitoji.witcher_contracts.dto.request;

import jakarta.validation.constraints.NotBlank;

public record WitcherSchoolDTO(
        @NotBlank(message = "name is mandatory")
        String name
) {
}
