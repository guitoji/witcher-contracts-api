package com.guitoji.witcher_contracts.repository;

import com.guitoji.witcher_contracts.model.Witcher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface WitcherRepository extends JpaRepository<Witcher, UUID> {

    Optional<Witcher> findByName(String name);

    boolean existsByName(String name);
}
