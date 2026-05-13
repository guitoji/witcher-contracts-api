package com.guitoji.witcher_contracts.service;

import com.guitoji.witcher_contracts.repository.KingdomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KingdomService {

    private final KingdomRepository kingdomRepository;
}
