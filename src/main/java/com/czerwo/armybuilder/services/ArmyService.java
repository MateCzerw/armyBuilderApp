package com.czerwo.armybuilder.services;

import com.czerwo.armybuilder.models.ArmyRepository;
import com.czerwo.armybuilder.models.data.Army;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArmyService {

    ArmyRepository armyRepository;

    public ArmyService(ArmyRepository armyRepository) {
        this.armyRepository = armyRepository;
    }

    public Optional<Army> findByName(String name){
        return armyRepository.findByName(name);
    }

    public List<Army> findAll() {
        return armyRepository.findAll();
    }

    public void save(Army army) {
        armyRepository.save(army);
    }

    public Optional<Army> findById(int id) {
        return armyRepository.findById(id);
    }

    public void deleteById(int id) {
        armyRepository.deleteById(id);
    }

}
