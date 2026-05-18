package com.guitoji.witcher_contracts.dto.response;

import com.guitoji.witcher_contracts.model.enums.ContractNivel;
import com.guitoji.witcher_contracts.model.enums.ContractStatus;

import java.math.BigDecimal;
import java.util.UUID;

public record ResultContractDTO(
        UUID id,
        String title,
        BigDecimal bounty,
        String description,
        ContractStatus status,
        ContractNivel nivel,
        ResultKingdomDTO kingdomDTO,
        ResultMonsterDTO monsterDTO
) {
}
