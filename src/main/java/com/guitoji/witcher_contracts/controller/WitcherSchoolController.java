package com.guitoji.witcher_contracts.controller;

import com.guitoji.witcher_contracts.dto.request.WitcherSchoolDTO;
import com.guitoji.witcher_contracts.dto.response.ResultWitcherSchoolByName;
import com.guitoji.witcher_contracts.dto.response.ResultWitcherSchoolDTO;
import com.guitoji.witcher_contracts.service.WitcherSchoolService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/witcher-schools")
@RequiredArgsConstructor
public class WitcherSchoolController implements GenericController{

    private final WitcherSchoolService witcherSchoolService;

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody WitcherSchoolDTO dto) {
        URI locate = getHeaderLocation(witcherSchoolService.save(dto).getId());
        return ResponseEntity.created(locate).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResultWitcherSchoolDTO> search(@PathVariable String id) {
        return ResponseEntity.ok(witcherSchoolService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<ResultWitcherSchoolByName>> searchByName(@RequestParam(required = true) String name) {
        return ResponseEntity.ok(witcherSchoolService.getSchoolByName(name));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) {
        witcherSchoolService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResultWitcherSchoolDTO> update(
            @PathVariable String id,
            @Valid @RequestBody WitcherSchoolDTO dto) {
        return ResponseEntity.ok(witcherSchoolService.update(id, dto));
    }
}
