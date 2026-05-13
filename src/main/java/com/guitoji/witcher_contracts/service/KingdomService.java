package com.guitoji.witcher_contracts.service;

import com.guitoji.witcher_contracts.dto.request.KingdomDTO;
import com.guitoji.witcher_contracts.mapper.KingdomMapper;
import com.guitoji.witcher_contracts.model.Kingdom;
import com.guitoji.witcher_contracts.repository.KingdomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
