package com.guitoji.witcher_contracts.controller;

import com.guitoji.witcher_contracts.service.MonsterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/monsters")
@RequiredArgsConstructor
public class MonsterController implements GenericController {

    private final MonsterService monsterService;
}
