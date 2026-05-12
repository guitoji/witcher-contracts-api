package com.guitoji.witcher_contracts.validation;

import com.guitoji.witcher_contracts.exception.DuplicatedEntityException;
import com.guitoji.witcher_contracts.model.Witcher;
import com.guitoji.witcher_contracts.repository.WitcherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class WitcherValidation {

    private final WitcherRepository witcherRepository;

    public void validate(Witcher witcher) {
        if (witcherAlreadyExists(witcher)) {
            throw new DuplicatedEntityException("Witcher already exists");
        }
    }

    private boolean witcherAlreadyExists(Witcher witcher) {
        Optional<Witcher> resultWitcher = witcherRepository.findByName(witcher.getName());

        return resultWitcher.isPresent() && !resultWitcher.get().getId().equals(witcher.getId());
    }
}
