package com.guitoji.witcher_contracts.controller;

import com.guitoji.witcher_contracts.dto.request.KingdomDTO;
import com.guitoji.witcher_contracts.service.KingdomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/kingdoms")
@RequiredArgsConstructor
public class KingdomController implements GenericController {

    private final KingdomService kingdomService;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody KingdomDTO dto) {
        URI locate = getHeaderLocation(kingdomService.save(dto).getId());
        return ResponseEntity.created(locate).build();
    }
}
