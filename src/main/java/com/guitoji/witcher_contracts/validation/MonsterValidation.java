package com.guitoji.witcher_contracts.validation;

import com.guitoji.witcher_contracts.exception.DuplicatedEntityException;
import com.guitoji.witcher_contracts.model.Monster;
import com.guitoji.witcher_contracts.repository.MonsterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MonsterValidation {

    private final MonsterRepository monsterRepository;

    public void validate(Monster monster) {
        if (monsterAlreadyExists(monster)) {
            throw new DuplicatedEntityException("Monster already exists");
        }
    }

    private boolean monsterAlreadyExists(Monster monster) {
        Optional<Monster> result = monsterRepository
                .findByCreatureNameAndClassification(monster.getCreatureName(), monster.getClassification());

        return result.isPresent() && !result.get().getId().equals(monster.getId());
    }
}
