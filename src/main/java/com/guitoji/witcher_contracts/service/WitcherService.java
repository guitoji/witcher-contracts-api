package com.guitoji.witcher_contracts.service;

import com.guitoji.witcher_contracts.dto.request.WitcherDTO;
import com.guitoji.witcher_contracts.dto.response.ResultWitcherDTO;
import com.guitoji.witcher_contracts.exception.NotFoundException;
import com.guitoji.witcher_contracts.mapper.WitcherMapper;
import com.guitoji.witcher_contracts.model.Witcher;
import com.guitoji.witcher_contracts.model.WitcherSchool;
import com.guitoji.witcher_contracts.model.enums.WitcherMastery;
import com.guitoji.witcher_contracts.repository.WitcherRepository;
import com.guitoji.witcher_contracts.validation.WitcherValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WitcherService {

    private final WitcherRepository witcherRepository;
    private final WitcherMapper witcherMapper;
    private final WitcherValidation witcherValidation;
    private final WitcherSchoolService witcherSchoolService;

    @Transactional
    public Witcher save(WitcherDTO dto) {
        Witcher witcher = witcherMapper.toEntity(dto);
        witcherValidation.validate(witcher);
        return witcherRepository.save(witcher);
    }

    @Transactional(readOnly = true)
    public ResultWitcherDTO findById(String id) {
        return witcherRepository.findById(UUID.fromString(id))
                .map(witcherMapper::toDTO)
                .orElseThrow(() -> new NotFoundException("Witcher not found"));
    }

    @Transactional(readOnly = true)
    public List<ResultWitcherDTO> findByExample(String name, WitcherMastery mastery, String schoolName) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("id")
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Witcher witcherExample = new Witcher();
        witcherExample.setName(name);
        witcherExample.setMastery(mastery);
        if (schoolName != null) {
            witcherExample.setSchool(witcherSchoolService.getWitcherSchoolToWitcherService(schoolName));
        }

        Example<Witcher> example = Example.of(witcherExample, matcher);

        return witcherRepository.findAll(example)
                .stream()
                .map(witcherMapper::toDTO)
                .toList();
    }

    @Transactional
    public void delete(String id) {
        Optional<Witcher> witcherOptional = witcherRepository.findById(UUID.fromString(id));

        if (witcherOptional.isEmpty()) {
            throw new NotFoundException("Witcher not found");
        }
        witcherRepository.delete(witcherOptional.get());
    }

    @Transactional
    public ResultWitcherDTO update(String id, WitcherDTO dto) {
        Witcher witcher = witcherRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new NotFoundException("Witcher not found"));

        if(!witcher.getSchool().getId().equals(dto.idSchool())) {
            witcher.setSchool(witcherSchoolService.findByIdReturningWitcherSchool(dto.idSchool()));
        }

        witcher.setName(dto.name());
        witcher.setMastery(dto.mastery());

        witcherValidation.validate(witcher);
        return witcherMapper.toDTO(witcherRepository.save(witcher));
    }
}
