package com.guitoji.witcher_contracts.service;

import com.guitoji.witcher_contracts.dto.request.ContractDTO;
import com.guitoji.witcher_contracts.dto.response.ResultContractDTO;
import com.guitoji.witcher_contracts.exception.NotFoundException;
import com.guitoji.witcher_contracts.mapper.ContractMapper;
import com.guitoji.witcher_contracts.model.Contract;
import com.guitoji.witcher_contracts.repository.ContractRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContractService {

    private final ContractRepository contractRepository;
    private final ContractMapper contractMapper;

    @Transactional
    public Contract save(ContractDTO dto) {
        Contract contract = contractMapper.toEntity(dto);
        return contractRepository.save(contract);
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
        contractRepository.delete(contractOptional.get());
    }
}
