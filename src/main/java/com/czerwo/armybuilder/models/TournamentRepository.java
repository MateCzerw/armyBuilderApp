package com.czerwo.armybuilder.models;

import com.czerwo.armybuilder.auth.ApplicationUser;
import com.czerwo.armybuilder.models.data.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TournamentRepository extends JpaRepository<Tournament, Integer> {
    Optional<Tournament> findByName(String name);

    List<Tournament> findAllByHost_Username(String username);

    List<Tournament> findAllByParticipants(ApplicationUser user);

    List<Tournament> findAllByParticipantsNotContaining(ApplicationUser user);


}
