package com.czerwo.armybuilder.models;

import com.czerwo.armybuilder.models.data.Army;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArmyRepository extends JpaRepository<Army, Integer> {
    Optional<Army> findByName(String name);
}
