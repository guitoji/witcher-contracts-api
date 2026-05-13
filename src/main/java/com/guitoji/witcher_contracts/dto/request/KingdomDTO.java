package com.guitoji.witcher_contracts.dto.request;

import jakarta.validation.constraints.NotBlank;

public record KingdomDTO(
        @NotBlank
        String name
) {
}
