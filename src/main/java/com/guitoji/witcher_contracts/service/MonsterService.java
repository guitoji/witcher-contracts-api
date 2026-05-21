package com.guitoji.witcher_contracts.service;

import com.guitoji.witcher_contracts.dto.request.MonsterDTO;
import com.guitoji.witcher_contracts.dto.response.ResultMonsterDTO;
import com.guitoji.witcher_contracts.exception.NotFoundException;
import com.guitoji.witcher_contracts.mapper.MonsterMapper;
import com.guitoji.witcher_contracts.model.Monster;
import com.guitoji.witcher_contracts.model.enums.MonsterClassification;
import com.guitoji.witcher_contracts.repository.MonsterRepository;
import com.guitoji.witcher_contracts.validation.MonsterValidation;
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
public class MonsterService {

    private final MonsterRepository monsterRepository;
    private final MonsterMapper monsterMapper;
    private final MonsterValidation monsterValidation;

    @Transactional
    public Monster save(MonsterDTO dto) {
        Monster monster = monsterMapper.toEntity(dto);
        monsterValidation.validate(monster);
        return monsterRepository.save(monster);
    }

    @Transactional(readOnly = true)
    public List<ResultMonsterDTO> findByExample(String creatureName, MonsterClassification classification) {
        Monster monster = new Monster();
        monster.setCreatureName(creatureName);
        monster.setClassification(classification);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withIgnorePaths("id")
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<Monster> example = Example.of(monster, matcher);

        return monsterRepository.findAll(example)
                .stream()
                .map(monsterMapper::toDTO)
                .toList();
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

                    monsterValidation.validate(monster);
                    return monsterMapper.toDTO(monsterRepository.save(monster));
                }).orElseThrow(() -> new NotFoundException("Monster not found"));
    }

    /**
     *
     * Methods used in other controllers
     *
     */

    public Monster getMonsterToContract(UUID idMonster) {
        return monsterRepository.findById(idMonster)
                .orElseThrow(() -> new NotFoundException("Monster not found"));
    }

    public Monster getByName(String creatureName) {
        return monsterRepository.findByCreatureNameContaining(creatureName)
                .orElseThrow(() -> new NotFoundException("Monster not found"));
    }
}
