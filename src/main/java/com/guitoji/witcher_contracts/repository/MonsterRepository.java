package com.guitoji.witcher_contracts.repository;

import com.guitoji.witcher_contracts.model.Monster;
import com.guitoji.witcher_contracts.model.enums.MonsterClassification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MonsterRepository extends JpaRepository<Monster, UUID> {

    Optional<Monster> findByCreatureNameAndClassification(String creatureName, MonsterClassification classification);
}
