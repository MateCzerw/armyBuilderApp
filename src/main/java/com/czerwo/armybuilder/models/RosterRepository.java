package com.czerwo.armybuilder.models;

import com.czerwo.armybuilder.models.data.Roster;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RosterRepository extends JpaRepository<Roster, Integer> {

    List<Roster> findByApplicationUserUsername(String username);

}
