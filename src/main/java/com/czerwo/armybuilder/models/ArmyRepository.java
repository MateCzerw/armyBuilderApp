package com.czerwo.armybuilder.models;

import com.czerwo.armybuilder.models.data.Army;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ArmyRepository extends JpaRepository<Army, Integer> {

    Optional<Army> findByName(String name);

    @Query("select army from Army army " +
            " left join fetch army.units" +
            " where army.id = :id")
    Optional<Army> findByIdWithUnits(@Param("id") int id);

}
