package com.guitoji.witcher_contracts.service;

import com.guitoji.witcher_contracts.dto.request.WitcherSchoolDTO;
import com.guitoji.witcher_contracts.dto.response.ResultWitcherSchoolByName;
import com.guitoji.witcher_contracts.dto.response.ResultWitcherSchoolDTO;
import com.guitoji.witcher_contracts.exception.NotFoundException;
import com.guitoji.witcher_contracts.mapper.WitcherSchoolMapper;
import com.guitoji.witcher_contracts.model.WitcherSchool;
import com.guitoji.witcher_contracts.repository.WitcherSchoolRepository;
import com.guitoji.witcher_contracts.validation.WitcherSchoolValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WitcherSchoolService {

    private final WitcherSchoolRepository witcherSchoolRepository;
    private final WitcherSchoolMapper witcherSchoolMapper;
    private final WitcherSchoolValidation witcherSchoolValidation;

    public WitcherSchool save(WitcherSchoolDTO dto) {
        WitcherSchool witcherSchool = witcherSchoolMapper.toEntity(dto);
        witcherSchoolValidation.validate(witcherSchool);
        return witcherSchoolRepository.save(witcherSchool);
    }

    public ResultWitcherSchoolDTO findById(String id) {
        return witcherSchoolRepository.findById(UUID.fromString(id))
                .map(witcherSchoolMapper::toDTO)
                .orElseThrow(() -> new NotFoundException("Witcher School not found"));
    }

    public List<ResultWitcherSchoolByName> getSchoolByName(String name) {
        return witcherSchoolRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(witcherSchoolMapper::getByNameDTO)
                .toList();
    }

    public void deleteById(String id) {
        Optional<WitcherSchool> witcherSchool = witcherSchoolRepository.findById(UUID.fromString(id));

        if (witcherSchool.isEmpty()) {
            throw new NotFoundException("Witcher School not found");
        }
        witcherSchoolRepository.delete(witcherSchool.get());
    }

    public ResultWitcherSchoolDTO update(String id, WitcherSchoolDTO dto) {
        return witcherSchoolRepository.findById(UUID.fromString(id))
                .map(witcherSchool -> {
                    witcherSchool.setName(dto.name());
                    witcherSchoolValidation.validate(witcherSchool);

                    witcherSchoolRepository.save(witcherSchool);
                    return witcherSchoolMapper.toDTO(witcherSchool);
                }).orElseThrow(() -> new NotFoundException("Witcher School not found"));
    }
}
