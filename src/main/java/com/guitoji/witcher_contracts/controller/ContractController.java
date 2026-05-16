package com.guitoji.witcher_contracts.controller;

import com.guitoji.witcher_contracts.service.ContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contracts")
@RequiredArgsConstructor
public class ContractController implements GenericController {

    private ContractService contractService;
}
