package com.guitoji.witcher_contracts.dto.request;

import jakarta.validation.constraints.NotBlank;

public record KingdomDTO(
        @NotBlank(message = "name is mandatory")
        String name
) {
}
