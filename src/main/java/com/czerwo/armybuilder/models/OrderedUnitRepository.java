package com.czerwo.armybuilder.models;

import com.czerwo.armybuilder.models.data.OrderedUnit;
import com.czerwo.armybuilder.models.data.Roster;
import com.czerwo.armybuilder.services.RosterService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderedUnitRepository extends JpaRepository<OrderedUnit, Integer> {

    List<OrderedUnit> findAllByRoster(Roster roster);
}
