package com.guitoji.witcher_contracts.service;

import com.guitoji.witcher_contracts.dto.request.ContractDTO;
import com.guitoji.witcher_contracts.mapper.ContractMapper;
import com.guitoji.witcher_contracts.model.Contract;
import com.guitoji.witcher_contracts.repository.ContractRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContractService {

    private final ContractRepository contractRepository;
    private final ContractMapper contractMapper;

    public Contract save(ContractDTO dto) {
        Contract contract = contractMapper.toEntity(dto);
        return contractRepository.save(contract);
    }
}
