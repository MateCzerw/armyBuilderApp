package com.czerwo.armybuilder.services;

import com.czerwo.armybuilder.models.OrderedUnitRepository;
import com.czerwo.armybuilder.models.RosterRepository;
import com.czerwo.armybuilder.models.UnitRepository;
import com.czerwo.armybuilder.models.data.OrderedUnit;
import com.czerwo.armybuilder.models.data.Roster;
import com.czerwo.armybuilder.models.data.Unit;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RosterService {

    RosterRepository rosterRepository;
    OrderedUnitRepository orderedUnitRepository;
    UnitRepository unitRepository;

    public RosterService(RosterRepository rosterRepository, OrderedUnitRepository orderedUnitRepository, UnitRepository unitRepository) {
        this.rosterRepository = rosterRepository;
        this.orderedUnitRepository = orderedUnitRepository;
        this.unitRepository = unitRepository;
    }

    public List<Roster> findAll() {return rosterRepository.findAll(); }


    public void save(Roster roster) {
        rosterRepository.save(roster);
    }

    public void deleteById(int id) {
        rosterRepository.deleteById(id);
    }

    public List<OrderedUnit> getListOfOrderedUnits(Roster roster) {
        return orderedUnitRepository.findAllByRoster(roster);
    }

    public List<Roster> findByUsername(String username){
        return rosterRepository.findByApplicationUserUsername(username);
    }

    public Optional<Roster> findById(int id) {
        return rosterRepository.findById(id);
    }

    public Optional<Unit> findUnitById(int unitId) {
        return unitRepository.findById(unitId);
    }

    public Optional<OrderedUnit> getOrderedUnit(int orderedUnitId) {
        return orderedUnitRepository.findById(orderedUnitId);
    }

    public void save(OrderedUnit orderedUnit) {
        orderedUnitRepository.save(orderedUnit);
    }
}
