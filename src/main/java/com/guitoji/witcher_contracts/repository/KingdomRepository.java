package com.guitoji.witcher_contracts.repository;

import com.guitoji.witcher_contracts.model.Kingdom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface KingdomRepository extends JpaRepository<Kingdom, UUID> {
}
