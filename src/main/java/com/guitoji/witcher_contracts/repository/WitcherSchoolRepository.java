package com.guitoji.witcher_contracts.repository;

import com.guitoji.witcher_contracts.model.WitcherSchool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface WitcherSchoolRepository extends JpaRepository<WitcherSchool, UUID> {
    List<WitcherSchool> findAllByNameContainingIgnoreCase(String name);

    Optional<WitcherSchool> findByName(String name);

    Optional<WitcherSchool> findByNameContainingIgnoreCase(String name);

    boolean existsByName(String name);
}
