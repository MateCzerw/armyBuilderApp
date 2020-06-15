package com.czerwo.armybuilder.models;

import com.czerwo.armybuilder.models.data.Army;
import com.czerwo.armybuilder.models.data.options.GroupOfOptions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface GroupOfOptionsRepository extends JpaRepository<GroupOfOptions, Integer> {


//    @Query("select groupOfOptions from GroupOfOptions groupOfOptions " +
//            " left join fetch groupOfOptions.options" +
//            " where groupOfOptions.id = :id")
//    Optional<GroupOfOptions> findGroupByIdWithOptions(@Param("id") int id);


    Optional<GroupOfOptions> findByName(String name);



}
