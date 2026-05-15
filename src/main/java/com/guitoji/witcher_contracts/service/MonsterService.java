package com.guitoji.witcher_contracts.service;

import com.guitoji.witcher_contracts.dto.request.MonsterDTO;
import com.guitoji.witcher_contracts.mapper.MonsterMapper;
import com.guitoji.witcher_contracts.model.Monster;
import com.guitoji.witcher_contracts.repository.MonsterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MonsterService {

    private final MonsterRepository monsterRepository;
    private final MonsterMapper monsterMapper;

    public Monster save(MonsterDTO dto) {
        Monster monster = monsterMapper.toEntity(dto);
        return monsterRepository.save(monster);
    }
}
