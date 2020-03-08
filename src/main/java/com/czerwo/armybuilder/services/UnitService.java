package com.czerwo.armybuilder.services;

import com.czerwo.armybuilder.models.ArmyRepository;
import com.czerwo.armybuilder.models.UnitRepository;
import com.czerwo.armybuilder.models.data.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UnitService {


    UnitRepository unitRepository;
    ArmyRepository armyRepository;

    @Autowired
    public UnitService(UnitRepository unitRepository, ArmyRepository armyRepository) {
        this.unitRepository = unitRepository;
        this.armyRepository = armyRepository;
    }


    public List<Unit> getUnits() {
        List<Unit> units = new ArrayList<>();
        units = unitRepository.findAll();
        return units;
    }

    public void save(Unit unit) {
        unitRepository.save(unit);
    }

    public Optional<Unit> findById(int id) {
        return unitRepository.findById(id);
    }

    public void deleteById(int id) {
        unitRepository.deleteById(id);
    }
}
