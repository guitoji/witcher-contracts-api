package com.guitoji.witcher_contracts.controller;

import com.guitoji.witcher_contracts.dto.request.WitcherDTO;
import com.guitoji.witcher_contracts.dto.response.ResultWitcherDTO;
import com.guitoji.witcher_contracts.service.WitcherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/witchers")
@RequiredArgsConstructor
public class WitcherController implements GenericController {

    private final WitcherService witcherService;

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody WitcherDTO dto) {
        URI locate = getHeaderLocation(witcherService.save(dto).getId());
        return ResponseEntity.created(locate).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResultWitcherDTO> searchById(@PathVariable String id) {
        return ResponseEntity.ok(witcherService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) {
        witcherService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResultWitcherDTO> update(@PathVariable String id, @RequestBody WitcherDTO dto) {
        return ResponseEntity.ok(witcherService.update(id, dto));
    }
}
