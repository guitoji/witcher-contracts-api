package com.guitoji.witcher_contracts.controller;

import com.guitoji.witcher_contracts.dto.request.WitcherDTO;
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
}
