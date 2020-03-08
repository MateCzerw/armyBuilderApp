package com.czerwo.armybuilder.models;

import com.czerwo.armybuilder.models.data.Unit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnitRepository extends JpaRepository<Unit, Integer> {
}
