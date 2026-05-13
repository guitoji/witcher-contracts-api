package com.guitoji.witcher_contracts.controller;

import com.guitoji.witcher_contracts.service.KingdomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kingdoms")
@RequiredArgsConstructor
public class KingdomController implements GenericController {

    private final KingdomService kingdomService;


}
