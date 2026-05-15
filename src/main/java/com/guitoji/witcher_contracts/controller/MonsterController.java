package com.guitoji.witcher_contracts.controller;

import com.guitoji.witcher_contracts.dto.request.MonsterDTO;
import com.guitoji.witcher_contracts.service.MonsterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
