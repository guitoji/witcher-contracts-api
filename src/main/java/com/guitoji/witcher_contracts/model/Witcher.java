package com.guitoji.witcher_contracts.model;

import com.guitoji.witcher_contracts.model.enums.WitcherMastery;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "witcher")
public class Witcher {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_school")
    private WitcherSchool school;

    @Column(name = "mastery")
    @Enumerated(EnumType.STRING)
    private WitcherMastery mastery;
}
