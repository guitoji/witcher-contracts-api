package com.guitoji.witcher_contracts.service;

import com.guitoji.witcher_contracts.dto.request.KingdomDTO;
import com.guitoji.witcher_contracts.dto.response.ResultKingdomDTO;
import com.guitoji.witcher_contracts.exception.NotFoundException;
import com.guitoji.witcher_contracts.mapper.KingdomMapper;
import com.guitoji.witcher_contracts.model.Kingdom;
import com.guitoji.witcher_contracts.repository.KingdomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class KingdomService {

    private final KingdomRepository kingdomRepository;
    private final KingdomMapper kingdomMapper;

    @Transactional
    public Kingdom save(KingdomDTO dto) {
        Kingdom kingdom = kingdomMapper.toEntity(dto);
        return kingdomRepository.save(kingdom);
    }

    @Transactional(readOnly = true)
    public ResultKingdomDTO findById(String id) {
        return kingdomRepository.findById(UUID.fromString(id))
                .map(kingdomMapper::toDTO)
                .orElseThrow(() -> new NotFoundException("Kingdom not found"));
    }

    @Transactional
    public void delete(String id) {
        Optional<Kingdom> kingdomOptional = kingdomRepository.findById(UUID.fromString(id));

        if (kingdomOptional.isEmpty()) {
            throw new NotFoundException("Kingdom not found");
        }
        kingdomRepository.delete(kingdomOptional.get());
    }

    @Transactional
    public ResultKingdomDTO update(String id, KingdomDTO dto) {
        return kingdomRepository.findById(UUID.fromString(id))
                .map(kingdom -> {
                    kingdom.setName(dto.name());

                    kingdomRepository.save(kingdom);
                    return kingdomMapper.toDTO(kingdom);
                }).orElseThrow(() -> new NotFoundException("Kingdom not found"));
    }
}
