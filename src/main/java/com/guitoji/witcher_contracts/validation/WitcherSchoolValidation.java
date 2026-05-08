package com.guitoji.witcher_contracts.validation;

import com.guitoji.witcher_contracts.exception.DuplicatedEntityException;
import com.guitoji.witcher_contracts.model.WitcherSchool;
import com.guitoji.witcher_contracts.repository.WitcherSchoolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class WitcherSchoolValidation {

    private final WitcherSchoolRepository witcherSchoolRepository;

    public void validate(WitcherSchool witcherSchool) {
        if (schoolAlreadyExists(witcherSchool)) {
            throw new DuplicatedEntityException("This Witcher School already exists");
        }
    }

    public boolean schoolAlreadyExists(WitcherSchool witcherSchool) {
        Optional<WitcherSchool> result = witcherSchoolRepository.findByName(witcherSchool.getName());

        return result.isPresent() && !result.get().getId().equals(witcherSchool.getId());
    }
}
