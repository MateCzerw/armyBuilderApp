package com.czerwo.armybuilder.services;



import com.czerwo.armybuilder.exceptions.DuplicateArmyException;
import com.czerwo.armybuilder.exceptions.NoRequestedArmyException;
import com.czerwo.armybuilder.models.ArmyRepository;
import com.czerwo.armybuilder.models.data.Army;
import com.czerwo.armybuilder.models.data.Roster;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class ArmyService {


    ArmyRepository armyRepository;

    public ArmyService(ArmyRepository armyRepository) {
        this.armyRepository = armyRepository;
    }

    public Optional<Army> findByName(String name) {
        return armyRepository.findByName(name);
    }

    public Optional<Army> findById(int id) {
        return armyRepository.findById(id);
    }

    public List<Army> findAll()   {
        return armyRepository.findAll();
    }

    public void save(Army army) {

     Optional<Army> armyByName = findByName(army.getName());

        armyByName.ifPresent(u -> {
            throw new DuplicateArmyException(army.getName());
        });


        Army save = armyRepository.save(army);
    }


    public void deleteById(int id) {

        Optional<Army> armyById = findById(id);

        armyById.orElseThrow(() -> new NoRequestedArmyException(id));

        armyRepository.deleteById(id);

    }

    public void update(Army army){
        Optional<Army> armyByName = armyRepository.findByName(army.getName());
        armyByName.ifPresent(u -> {
            if(u.getId() != army.getId())  throw new DuplicateArmyException(army.getName());
        });

            armyRepository.save(army);

    }


    public Optional<Army> findByIdWithUnits(int armyId) {

        return armyRepository.findByIdWithUnits(armyId);
    }


}


//    public void save(Army army, MultipartFile file)  {
//
//
//        Map uploadResult = null;
//
//
//        try {
//            uploadResult =  cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        uploadResult.get("url").toString();
//
//        army.setPicture(uploadResult.get("url").toString());
//        armyRepository.save(army);
//    }
