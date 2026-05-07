package com.guitoji.witcher_contracts.service;

import com.guitoji.witcher_contracts.repository.WitcherSchoolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WitcherSchoolService {

    private final WitcherSchoolRepository witcherSchoolRepository;
}
