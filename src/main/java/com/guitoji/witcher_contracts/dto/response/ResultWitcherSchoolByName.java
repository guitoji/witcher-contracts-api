package com.guitoji.witcher_contracts.dto.response;

import java.util.UUID;

public record ResultWitcherSchoolByName(
        UUID id,
        String name
) {
}
