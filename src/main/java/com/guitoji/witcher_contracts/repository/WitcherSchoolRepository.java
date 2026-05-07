package com.guitoji.witcher_contracts.repository;

import com.guitoji.witcher_contracts.model.WitcherSchool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WitcherSchoolRepository extends JpaRepository<WitcherSchool, UUID> {
}
