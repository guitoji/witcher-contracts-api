package com.guitoji.witcher_contracts.repository;

import com.guitoji.witcher_contracts.model.Monster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MonsterRepository extends JpaRepository<Monster, UUID> {
}
