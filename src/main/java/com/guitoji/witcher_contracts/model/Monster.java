package com.guitoji.witcher_contracts.model;

import com.guitoji.witcher_contracts.model.enums.MonsterClassification;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "monster")
public class Monster {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "creature_name")
    private String creatureName;

    @Column(name = "class")
    @Enumerated(EnumType.STRING)
    private MonsterClassification classification;
}
