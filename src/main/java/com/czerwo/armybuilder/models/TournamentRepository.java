package com.czerwo.armybuilder.models;

import com.czerwo.armybuilder.models.data.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TournamentRepository extends JpaRepository<Tournament, Integer> {
}
