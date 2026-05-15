package com.guitoji.witcher_contracts.service;

import com.guitoji.witcher_contracts.dto.request.MonsterDTO;
import com.guitoji.witcher_contracts.dto.response.ResultMonsterDTO;
import com.guitoji.witcher_contracts.exception.NotFoundException;
import com.guitoji.witcher_contracts.mapper.MonsterMapper;
import com.guitoji.witcher_contracts.model.Monster;
import com.guitoji.witcher_contracts.repository.MonsterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MonsterService {

    private final MonsterRepository monsterRepository;
    private final MonsterMapper monsterMapper;

    @Transactional
    public Monster save(MonsterDTO dto) {
        Monster monster = monsterMapper.toEntity(dto);
        return monsterRepository.save(monster);
    }

    @Transactional(readOnly = true)
    public ResultMonsterDTO findById(String id) {
        return monsterRepository.findById(UUID.fromString(id))
                .map(monsterMapper::toDTO)
                .orElseThrow(() -> new NotFoundException("Monster not found"));
    }

    @Transactional
    public void delete(String id) {
        Optional<Monster> monsterOptional = monsterRepository.findById(UUID.fromString(id));

        if (monsterOptional.isEmpty()) {
            throw new NotFoundException("Monster not found");
        }
        monsterRepository.delete(monsterOptional.get());
    }

    @Transactional
    public ResultMonsterDTO update(String id, MonsterDTO dto) {
        return monsterRepository.findById(UUID.fromString(id))
                .map(monster -> {
                    monster.setCreatureName(dto.creatureName());
                    monster.setClassification(dto.classification());

                    return monsterMapper.toDTO(monsterRepository.save(monster));
                }).orElseThrow(() -> new NotFoundException("Monster not found"));
    }
}
