package com.guitoji.witcher_contracts.controller;

import com.guitoji.witcher_contracts.service.WitcherSchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/witcher-schools")
@RequiredArgsConstructor
public class WitcherSchoolController {

    private final WitcherSchoolService witcherSchoolService;
}
