package com.guitoji.witcher_contracts.controller;

import com.guitoji.witcher_contracts.dto.request.ContractDTO;
import com.guitoji.witcher_contracts.dto.response.ResultContractDTO;
import com.guitoji.witcher_contracts.service.ContractService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/contracts")
@RequiredArgsConstructor
public class ContractController implements GenericController {

    private final ContractService contractService;

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody ContractDTO dto) {
        URI locate = getHeaderLocation(contractService.save(dto).getId());
        return ResponseEntity.created(locate).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResultContractDTO> searchById(@PathVariable String id) {
        return ResponseEntity.ok(contractService.findById(id));
    }
}
