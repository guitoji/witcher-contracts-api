package com.guitoji.witcher_contracts.controller;

import com.guitoji.witcher_contracts.dto.request.MonsterDTO;
import com.guitoji.witcher_contracts.dto.response.ResultMonsterDTO;
import com.guitoji.witcher_contracts.service.MonsterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/monsters")
@RequiredArgsConstructor
public class MonsterController implements GenericController {

    private final MonsterService monsterService;

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody MonsterDTO dto) {
        URI locate = getHeaderLocation(monsterService.save(dto).getId());
        return ResponseEntity.created(locate).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResultMonsterDTO> searchById(@PathVariable String id) {
        return ResponseEntity.ok(monsterService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) {
        monsterService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResultMonsterDTO> update(@PathVariable String id, @Valid @RequestBody MonsterDTO dto) {
        return ResponseEntity.ok(monsterService.update(id, dto));
    }
}
