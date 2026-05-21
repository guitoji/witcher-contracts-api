package com.guitoji.witcher_contracts.controller;

import com.guitoji.witcher_contracts.dto.request.ContractDTO;
import com.guitoji.witcher_contracts.dto.response.ResultContractDTO;
import com.guitoji.witcher_contracts.model.enums.ContractNivel;
import com.guitoji.witcher_contracts.model.enums.ContractStatus;
import com.guitoji.witcher_contracts.service.ContractService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<ResultContractDTO>> search(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) BigDecimal bounty,
            @RequestParam(required = false) ContractStatus status,
            @RequestParam(required = false) ContractNivel nivel,
            @RequestParam(required = false) String kingdomName,
            @RequestParam(required = false) String creatureName) {
        return ResponseEntity.ok(contractService.findAll(title, bounty, status, nivel, kingdomName, creatureName));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResultContractDTO> searchById(@PathVariable String id) {
        return ResponseEntity.ok(contractService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) {
        contractService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResultContractDTO> update(@PathVariable String id, @Valid @RequestBody ContractDTO dto) {
        return ResponseEntity.ok(contractService.update(id, dto));
    }
}
