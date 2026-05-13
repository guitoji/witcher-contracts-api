package com.guitoji.witcher_contracts.controller;

import com.guitoji.witcher_contracts.dto.request.KingdomDTO;
import com.guitoji.witcher_contracts.dto.response.ResultKingdomDTO;
import com.guitoji.witcher_contracts.service.KingdomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public ResponseEntity<ResultKingdomDTO> searchById(@PathVariable String id) {
         return ResponseEntity.ok(kingdomService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) {
        kingdomService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResultKingdomDTO> update(@PathVariable String id, @RequestBody KingdomDTO dto) {
        return ResponseEntity.ok(kingdomService.update(id, dto));
    }
}
