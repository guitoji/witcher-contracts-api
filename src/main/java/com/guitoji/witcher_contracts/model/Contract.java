package com.guitoji.witcher_contracts.model;

import com.guitoji.witcher_contracts.model.enums.ContractNivel;
import com.guitoji.witcher_contracts.model.enums.ContractStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "contract")
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "title")
    private String title;

    @Column(name = "bounty")
    private BigDecimal bounty;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_kingdom")
    private Kingdom kingdom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_monster")
    private Monster monster;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ContractStatus status;

    @Column(name = "nivel")
    @Enumerated(EnumType.STRING)
    private ContractNivel nivel;
}
