package com.guitoji.witcher_contracts.validation;

import com.guitoji.witcher_contracts.exception.DuplicatedEntityException;
import com.guitoji.witcher_contracts.model.Kingdom;
import com.guitoji.witcher_contracts.repository.KingdomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class KingdomValidation {

    private final KingdomRepository kingdomRepository;

    public void validate(Kingdom kingdom) {
        if (kingdomAlreadyExists(kingdom)) {
            throw new DuplicatedEntityException("Kingdom already exists");
        }
    }

    private boolean kingdomAlreadyExists(Kingdom kingdom) {
        Optional<Kingdom> result = kingdomRepository.findByName(kingdom.getName());

        return result.isPresent() && !result.get().getId().equals(kingdom.getId());
    }
}
