package com.guitoji.witcher_contracts.service;

import com.guitoji.witcher_contracts.dto.request.WitcherDTO;
import com.guitoji.witcher_contracts.dto.response.ResultWitcherDTO;
import com.guitoji.witcher_contracts.exception.NotFoundException;
import com.guitoji.witcher_contracts.mapper.WitcherMapper;
import com.guitoji.witcher_contracts.model.Witcher;
import com.guitoji.witcher_contracts.repository.WitcherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WitcherService {

    private final WitcherRepository witcherRepository;
    private final WitcherMapper witcherMapper;

    @Transactional
    public Witcher save(WitcherDTO dto) {
        Witcher witcher = witcherMapper.toEntity(dto);
        return witcherRepository.save(witcher);
    }

    @Transactional(readOnly = true)
    public ResultWitcherDTO findById(String id) {
        return witcherRepository.findById(UUID.fromString(id))
                .map(witcherMapper::toDTO)
                .orElseThrow(() -> new NotFoundException("Witcher not found"));
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
        return witcherRepository.findById(UUID.fromString(id))
                .map(witcher -> {
                    if (witcher.getSchool().getId() != dto.idSchool()) {
                        Witcher temporaryWitcher = witcherMapper.toEntity(dto);

                        witcher.setSchool(temporaryWitcher.getSchool());
                        witcher.setName(temporaryWitcher.getName());
                        witcher.setMastery(temporaryWitcher.getMastery());

                        return witcherMapper.toDTO(witcherRepository.save(witcher));
                    }

                    witcher.setName(dto.name());
                    witcher.setMastery(dto.mastery());

                    return witcherMapper.toDTO(witcherRepository.save(witcher));
                }).orElseThrow(() -> new NotFoundException("Witcher not found"));
    }
}
