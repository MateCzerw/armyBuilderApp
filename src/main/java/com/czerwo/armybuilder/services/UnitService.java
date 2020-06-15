package com.czerwo.armybuilder.services;

import com.czerwo.armybuilder.exceptions.GroupIsAlreadyInUnitException;
import com.czerwo.armybuilder.exceptions.NoRequestedGroupException;
import com.czerwo.armybuilder.exceptions.NoRequestedUnitException;
import com.czerwo.armybuilder.models.ArmyRepository;
import com.czerwo.armybuilder.models.GroupOfOptionsRepository;
import com.czerwo.armybuilder.models.UnitDetailsRepository;
import com.czerwo.armybuilder.models.UnitRepository;
import com.czerwo.armybuilder.models.data.Army;
import com.czerwo.armybuilder.models.data.Unit;
import com.czerwo.armybuilder.models.data.UnitDetails;
import com.czerwo.armybuilder.models.data.options.GroupOfOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UnitService {


    UnitRepository unitRepository;
    ArmyRepository armyRepository;
    UnitDetailsRepository unitDetailsRepository;
    GroupOfOptionsRepository groupOfOptionsRepository;

    public UnitService(UnitRepository unitRepository,
                       ArmyRepository armyRepository,
                       UnitDetailsRepository unitDetailsRepository,
                       GroupOfOptionsRepository groupOfOptionsRepository) {
        this.unitRepository = unitRepository;
        this.armyRepository = armyRepository;
        this.unitDetailsRepository = unitDetailsRepository;
        this.groupOfOptionsRepository = groupOfOptionsRepository;
    }

    public List<Unit> getUnits() {
        List<Unit> units = new ArrayList<>();
        units = unitRepository.findAll();
        return units;
    }

    public void save(Unit unit, int armyId) {
        Optional<Army> armyById = armyRepository.findById(armyId);
        UnitDetails unitDetails = new UnitDetails();
        setUnitDetailsStatistics(unitDetails);
        armyById.ifPresent(army -> {
            unit.setArmy(army);
            unit.setUnitDetails(unitDetails);
            unitRepository.save(unit);
        });

    }

    private void setUnitDetailsStatistics(UnitDetails unitDetails) {
        unitDetails.setAdvanceRate(5);
        unitDetails.setMarchRate(5);
        unitDetails.setDiscipline(5);
        unitDetails.setHealthPoints(5);
        unitDetails.setDefensiveSkill(5);
        unitDetails.setResilience(5);
        unitDetails.setArmour(5);
        unitDetails.setAgility(5);
        unitDetails.setAttackValue(5);
        unitDetails.setOffensiveSkill(5);
        unitDetails.setStrength(5);
        unitDetails.setArmourPenetration(5);
    }

    public Optional<Unit> findById(int id) {
        return unitRepository.findById(id);
    }

    public void deleteById(int id) {
        unitRepository.deleteById(id);
    }

    public  Optional<UnitDetails> getUnitDetails(int id) {
        return unitDetailsRepository.findById(id);
    }

    public void saveUnitDetails(UnitDetails unitDetails) {
        unitDetailsRepository.save(unitDetails);
    }


    public Optional<Unit> findByName(String name) {
        return unitRepository.findByName(name);
    }

    public void save(Unit unit) {
        unitRepository.save(unit);
    }

    public void addGroupOfOptionsToUnit(int unitId, List<Integer> groupList) {

        Optional<Unit> unitById = unitRepository.findById(unitId);
        Unit unit = unitById.orElseThrow(() -> new NoRequestedUnitException(unitId));

        for (GroupOfOptions groupOfOptions : unit.getGroupsOfOptions()) {
            for (int groupId : groupList) {
                if(groupOfOptions.getId() == groupId) throw new GroupIsAlreadyInUnitException(groupId);
            }

        }


        for (int groupId : groupList) {
            Optional<GroupOfOptions> groupOfOptionsById = groupOfOptionsRepository.findById(groupId);
            GroupOfOptions groupOfOptions = groupOfOptionsById.orElseThrow(() -> new NoRequestedGroupException(groupId));
            unit.addGroupOfOptions(groupOfOptions);
            groupOfOptions.addUnit(unit);
        }

        unitRepository.save(unit);
    }

    public void removeGroupFromUnit(int groupId, int unitId) {

        Optional<GroupOfOptions> groupById = groupOfOptionsRepository.findById(groupId);
        Optional<Unit> unitById = unitRepository.findById(unitId);

        GroupOfOptions groupOfOptions = groupById.orElseThrow(() -> new NoRequestedGroupException(groupId));
        Unit unit = unitById.orElseThrow(() -> new NoRequestedUnitException(unitId));

        unit.removeGroup(groupOfOptions);
        groupOfOptions.removeFromUnit(unit);

        unitRepository.save(unit);
    }

}
