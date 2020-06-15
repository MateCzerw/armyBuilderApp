package com.czerwo.armybuilder.services;

import com.czerwo.armybuilder.exceptions.*;
import com.czerwo.armybuilder.models.GroupOfOptionsRepository;
import com.czerwo.armybuilder.models.OptionRepository;
import com.czerwo.armybuilder.models.data.options.GroupOfOptions;
import com.czerwo.armybuilder.models.data.options.Option;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class OptionService {

    private GroupOfOptionsRepository groupOfOptionsRepository;

    private OptionRepository optionRepository;

    public OptionService(GroupOfOptionsRepository groupOfOptionsRepository, OptionRepository optionRepository) {
        this.groupOfOptionsRepository = groupOfOptionsRepository;
        this.optionRepository = optionRepository;
    }

    public List<GroupOfOptions> findAll() {
        return groupOfOptionsRepository.findAll();
    }

    public Optional<GroupOfOptions> findGroupById(int groupId) {
        return groupOfOptionsRepository.findById(groupId);
    }


    public Optional<GroupOfOptions> findGroupByName(String name) {
        return groupOfOptionsRepository.findByName(name);
    }

    public void deleteGroupById(int groupId) {
        groupOfOptionsRepository.deleteById(groupId);
    }

    public void saveGroup(GroupOfOptions groupOfOptions) {

        Optional<GroupOfOptions> groupByName = groupOfOptionsRepository.findByName(groupOfOptions.getName());
        if (!groupByName.isPresent()) {

            groupOfOptionsRepository.save(groupOfOptions);
        } else throw new DuplicateGroupException(groupOfOptions.getName());

    }

    public void updateGroup(GroupOfOptions groupOfOptions) {

        Optional<GroupOfOptions> groupById = groupOfOptionsRepository.findById(groupOfOptions.getId());

        GroupOfOptions group = groupById.orElseThrow(() -> new NoRequestedGroupException(groupOfOptions.getId()));

        group.setName(groupOfOptions.getName());
        groupOfOptionsRepository.save(group);


    }

//todo Hibernate n+1 problem solution refactored from one to many into many to many
    public Optional<GroupOfOptions> findGroupByIdWithOptions(int groupId) {
        //return groupOfOptionsRepository.findGroupByIdWithOptions(groupId);
        return groupOfOptionsRepository.findById(groupId);
    }

    //todo
    public void createOptionAndAddToGroup(Option option, int groupId) {

        Optional<GroupOfOptions> groupById = groupOfOptionsRepository.findById(groupId);
        optionRepository.findByName(option.getName()).ifPresent((it) -> new DuplicateOptionException(option.getName()));
        GroupOfOptions groupOfOptions = groupById.orElseThrow(() -> new NoRequestedGroupException(groupId));

        option.addGroupOfOptions(groupOfOptions);
        optionRepository.save(option);
    }


    //todo find all which are not present in current group
    public List<Option> findAllOptions() {
        return optionRepository.findAll();
    }

    //todo required test for adding options which are already in db,maybe it can make some problem during save to db
    @Transactional
    public void addOptionsToGroup(List<Integer> optionList, int groupId) {

        Optional<GroupOfOptions> groupOfOptionsById = groupOfOptionsRepository.findById(groupId);
        GroupOfOptions groupOfOptions = groupOfOptionsById.orElseThrow(() -> new NoRequestedGroupException(groupId));

        for (Option option : groupOfOptions.getOptions()) {
            for (int optionId : optionList) {
                if(option.getId() == optionId) throw new OptionIsAlreadyInGroupException(optionId);
            }

        }

        for (int optionId : optionList) {
            Optional<Option> optionById = optionRepository.findById(optionId);
            Option option = optionById.orElseThrow(() -> new NoRequestedOptionException(optionId));
            groupOfOptions.addOption(option);
            option.addGroupOfOptions(groupOfOptions);
        }

        groupOfOptionsRepository.save(groupOfOptions);

    }
//todo Its required to add delete unplug option from group of options
    public void removeOptionFromGroup(int groupId, int optionId) {

        Optional<GroupOfOptions> groupById = groupOfOptionsRepository.findById(groupId);
        Optional<Option> optionById = optionRepository.findById(optionId);

        GroupOfOptions groupOfOptions = groupById.orElseThrow(() -> new NoRequestedGroupException(groupId));
        Option option = optionById.orElseThrow(() -> new NoRequestedOptionException(optionId));

        groupOfOptions.removeOption(option);
        option.removeFromGroup(groupOfOptions);

        groupOfOptionsRepository.save(groupOfOptions);
    }
}