package com.guitoji.witcher_contracts.dto.request;

import com.guitoji.witcher_contracts.model.enums.ContractNivel;
import com.guitoji.witcher_contracts.model.enums.ContractStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record ContractDTO(
        @NotBlank(message = "title is mandatory")
        String title,
        @NotNull
        BigDecimal bounty,
        @NotBlank(message = "description is mandatory")
        String description,
        @NotNull(message = "status is mandatory")
        ContractStatus status,
        @NotNull(message = "nivel is mandatory")
        ContractNivel nivel,
        @NotNull(message = "the kingdom id is mandatory")
        UUID idKingdom,
        @NotNull(message = "the monster id is mandatory")
        UUID idMonster
) {
}
