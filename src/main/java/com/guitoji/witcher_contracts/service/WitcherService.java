package com.guitoji.witcher_contracts.service;

import com.guitoji.witcher_contracts.dto.request.WitcherDTO;
import com.guitoji.witcher_contracts.mapper.WitcherMapper;
import com.guitoji.witcher_contracts.model.Witcher;
import com.guitoji.witcher_contracts.repository.WitcherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
