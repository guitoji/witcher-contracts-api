package com.guitoji.witcher_contracts.service;

import com.guitoji.witcher_contracts.dto.request.ContractDTO;
import com.guitoji.witcher_contracts.dto.response.ResultContractDTO;
import com.guitoji.witcher_contracts.exception.NotFoundException;
import com.guitoji.witcher_contracts.mapper.ContractMapper;
import com.guitoji.witcher_contracts.model.Contract;
import com.guitoji.witcher_contracts.model.Kingdom;
import com.guitoji.witcher_contracts.model.Monster;
import com.guitoji.witcher_contracts.model.enums.ContractNivel;
import com.guitoji.witcher_contracts.model.enums.ContractStatus;
import com.guitoji.witcher_contracts.repository.ContractRepository;
import com.guitoji.witcher_contracts.validation.ContractValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContractService {

    private final ContractRepository contractRepository;
    private final ContractMapper contractMapper;
    private final ContractValidation contractValidation;
    private final KingdomService kingdomService;
    private final MonsterService monsterService;

    @Transactional
    public Contract save(ContractDTO dto) {
        Contract contract = contractMapper.toEntity(dto);
        contractValidation.validate(contract);
        return contractRepository.save(contract);
    }

    @Transactional(readOnly = true)
    public List<ResultContractDTO> findAll(
            String title, BigDecimal bounty, ContractStatus status, ContractNivel nivel, String kingdomName, String creatureName) {

        Contract contractExample = new Contract();

        Kingdom kingdom = new Kingdom();
        if (kingdomName != null) {
            kingdom = kingdomService.getByName(kingdomName);
            contractExample.setKingdom(kingdom);
        }

        Monster monster = new Monster();
        if (creatureName != null) {
            monster = monsterService.getByName(creatureName);
            contractExample.setMonster(monster);
        }

        contractExample.setTitle(title);
        contractExample.setBounty(bounty);
        contractExample.setStatus(status);
        contractExample.setNivel(nivel);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withIgnorePaths("id", "description")
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<Contract> example = Example.of(contractExample, matcher);

        return contractRepository.findAll(example)
                .stream()
                .map(contractMapper::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public ResultContractDTO findById(String id) {
        return contractRepository.findById(UUID.fromString(id))
                .map(contractMapper::toDTO)
                .orElseThrow(() -> new NotFoundException("Contract not found"));
    }

    @Transactional
    public void delete(String id) {
        Optional<Contract> contractOptional = contractRepository.findById(UUID.fromString(id));

        if (contractOptional.isEmpty()) {
            throw new NotFoundException("Contract not found");
        }
        contractValidation.validateDelete(contractOptional.get());
        contractRepository.delete(contractOptional.get());
    }

    @Transactional
    public ResultContractDTO update(String id, ContractDTO dto) {
        Contract contract = contractRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new NotFoundException("Contract not found"));

        if (!contract.getKingdom().getId().equals(dto.idKingdom())) {
            Kingdom kingdom = kingdomService.getKingdomToContract(dto.idKingdom());
            contract.setKingdom(kingdom);
        }

        if (!contract.getMonster().getId().equals(dto.idMonster())) {
            Monster monster = monsterService.getMonsterToContract(dto.idMonster());
            contract.setMonster(monster);
        }

        contract.setTitle(dto.title());
        contract.setBounty(dto.bounty());
        contract.setDescription(dto.description());
        contract.setStatus(dto.status());
        contract.setNivel(dto.nivel());

        return contractMapper.toDTO(contractRepository.save(contract));
    }
}
