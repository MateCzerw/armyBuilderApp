package com.czerwo.armybuilder.services;

import com.czerwo.armybuilder.auth.ApplicationUser;
import com.czerwo.armybuilder.auth.ApplicationUserRepository;
import com.czerwo.armybuilder.exceptions.*;
import com.czerwo.armybuilder.models.*;
import com.czerwo.armybuilder.models.data.Army;
import com.czerwo.armybuilder.models.data.OrderedUnit;
import com.czerwo.armybuilder.models.data.Roster;
import com.czerwo.armybuilder.models.data.Unit;
import com.czerwo.armybuilder.models.data.options.Option;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class RosterService {

    RosterRepository rosterRepository;
    ArmyRepository armyRepository;
    OrderedUnitRepository orderedUnitRepository;
    UnitRepository unitRepository;
    ApplicationUserRepository applicationUserRepository;
    OptionRepository optionRepository;

    public RosterService(RosterRepository rosterRepository, ArmyRepository armyRepository, OrderedUnitRepository orderedUnitRepository, UnitRepository unitRepository, ApplicationUserRepository applicationUserRepository, OptionRepository optionRepository) {
        this.rosterRepository = rosterRepository;
        this.armyRepository = armyRepository;
        this.orderedUnitRepository = orderedUnitRepository;
        this.unitRepository = unitRepository;
        this.applicationUserRepository = applicationUserRepository;
        this.optionRepository = optionRepository;
    }

    public List<Roster> findAll() {
        return rosterRepository.findAll();
    }


    public void save(Roster roster) {
        rosterRepository.save(roster);
    }

    //todo check if user deletes his own roster
    public void deleteById(int id) {
        Optional<Roster> rosterById = rosterRepository.findById(id);
        rosterById.orElseThrow(() -> new NoRequestedRosterException(id));

        rosterRepository.deleteById(id);
    }

    public List<OrderedUnit> getListOfOrderedUnits(Roster roster) {
        return orderedUnitRepository.findAllByRoster(roster);
    }

    public List<Roster> findByUsername(String username) {
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


    public void addNewRoster(Roster roster, int armyId, Principal principal) {

        Optional<Army> armyById = armyRepository.findById(armyId);
        Army army = armyById.orElseThrow(() -> new NoRequestedArmyException(armyId));
        Optional<Roster> rosterByName = rosterRepository.findByName(roster.getName());
        rosterByName.ifPresent(it -> new DuplicateRosterException(roster.getName()));
        Optional<ApplicationUser> userByName = applicationUserRepository.getApplicationUserByUsername(principal.getName());
        ApplicationUser user = userByName.orElseThrow(() -> new NoRequestedUserException(principal.getName()));

        roster.setArmy(army);

        roster.setApplicationUser(user);
        rosterRepository.save(roster);

    }

    @Transactional
    public void editRoster(Roster roster, int armyId, Principal principal) {

        Optional<Army> armyById = armyRepository.findById(armyId);
        Army army = armyById.orElseThrow(() -> new NoRequestedArmyException(armyId));


        Optional<ApplicationUser> userByName = applicationUserRepository.getApplicationUserByUsername(principal.getName());

        ApplicationUser user = userByName.orElseThrow(() -> new NoRequestedUserException(principal.getName()));

        Roster rosterById = rosterRepository.findById(roster.getId()).orElseThrow(() -> new NoRequestedRosterException(roster.getId()));

        Optional<Roster> rosterByName = rosterRepository.findByName(roster.getName());
        rosterByName.ifPresent(it -> {
                    if (it.getId() != roster.getId()) new DuplicateRosterException(roster.getName());
                }
        );


        rosterById.setName(roster.getName());
        if (rosterById.getArmy().getId() != armyId) {
            rosterById.setArmy(army);
            for (OrderedUnit orderedUnit : rosterById.getOrderedUnits()) {
                orderedUnitRepository.deleteById(orderedUnit.getId());
            }
            rosterById.removeOrderedUnits();
        }
        rosterRepository.save(rosterById);


    }

    //todo
    public void addUnitToRoster(int rosterId, OrderedUnit orderedUnit, int unitId) {

        Roster roster = rosterRepository.findById(rosterId).orElseThrow(() -> new NoRequestedRosterException(rosterId));
        Unit unit = unitRepository.findById(unitId).orElseThrow(() -> new NoRequestedUnitException(unitId));

        orderedUnit.setNumberOfModels(unit.getMinSizeOfUnit());
        orderedUnit.setUnit(unit);
        orderedUnit.setRoster(roster);
        roster.addUnit(orderedUnit);
        rosterRepository.save(roster);
    }

    @Transactional
    public void deleteOrderedUnitFromRoster(int orderedUnitId, int rosterId) {
        Roster roster = rosterRepository.findById(rosterId).orElseThrow(() -> new NoRequestedRosterException(rosterId));
        OrderedUnit orderedUnit = orderedUnitRepository.findById(orderedUnitId).orElseThrow(() -> new NoRequestedOrderedUnitException(orderedUnitId));

        if(roster.getOrderedUnits().contains(orderedUnit)){
            roster.removeOrderedUnit(orderedUnit);
            orderedUnitRepository.delete(orderedUnit);
            rosterRepository.save(roster);
        }else throw new NoRequestedOrderedUnitException(orderedUnitId);

    }

    public Optional<OrderedUnit> findOrderedUnitById(int orderedUnitId) {
        return orderedUnitRepository.findById(orderedUnitId);
    }

    @Transactional
    public void editOrderedUnit(int orderedUnitId, int numberOfModels, List<Integer> options) {

        OrderedUnit orderedUnit = orderedUnitRepository.findById(orderedUnitId).orElseThrow(() -> new NoRequestedOrderedUnitException(orderedUnitId));

        orderedUnit.setNumberOfModels(numberOfModels);

        orderedUnit.removeAllOptions();

        for (int optionId: options) {
            Option option = optionRepository.findById(optionId).orElseThrow(() -> new NoRequestedOptionException(optionId));
            orderedUnit.addChosenOption(option);
        }
        orderedUnitRepository.save(orderedUnit);

    }
}
