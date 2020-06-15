package com.czerwo.armybuilder.services;

import com.czerwo.armybuilder.auth.ApplicationUser;
import com.czerwo.armybuilder.auth.ApplicationUserRepository;
import com.czerwo.armybuilder.exceptions.*;
import com.czerwo.armybuilder.models.TournamentRepository;
import com.czerwo.armybuilder.models.data.Tournament;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class TournamentService {

    TournamentRepository tournamentRepository;
    ApplicationUserRepository applicationUserRepository;

    public TournamentService(TournamentRepository tournamentRepository, ApplicationUserRepository applicationUserRepository) {
        this.tournamentRepository = tournamentRepository;
        this.applicationUserRepository = applicationUserRepository;
    }

    public List<Tournament> findAll() {
        return tournamentRepository.findAll();
    }

    public void addTournament(Tournament tournament, String userName, String tournamentTime) throws ParseException {

        ApplicationUser user = applicationUserRepository.getApplicationUserByUsername(userName).orElseThrow(() -> new NoRequestedUserException(userName));
        tournamentRepository.findByName(tournament.getName()).ifPresent(it -> new DuplicateTournamentException(tournament.getName()));


        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");

        Date date = (Date)formatter.parse(tournamentTime);
        tournament.setTime(date);
        tournament.setHost(user);

        tournamentRepository.save(tournament);


    }


    public List<Tournament> findOwnTournaments(String username) {
        ApplicationUser user = applicationUserRepository.getApplicationUserByUsername(username).orElseThrow(() -> new NoRequestedUserException(username));

        return tournamentRepository.findAllByHost_Username(user.getUsername());
    }

    public List<Tournament> findSignedUpTournaments(String username) {
        ApplicationUser user = applicationUserRepository.getApplicationUserByUsername(username).orElseThrow(() -> new NoRequestedUserException(username));


        return tournamentRepository.findAllByParticipants(user);
    }

    public List<Tournament> findAllByParticipantsNotContaining(String username) {
        ApplicationUser user = applicationUserRepository.getApplicationUserByUsername(username).orElseThrow(() -> new NoRequestedUserException(username));


        return tournamentRepository.findAllByParticipantsNotContaining(user);
    }

    @Transactional
    public void signupForTournament(int tournamentId, String username) {
        ApplicationUser user = applicationUserRepository.getApplicationUserByUsername(username).orElseThrow(() -> new NoRequestedUserException(username));
        Tournament tournament = tournamentRepository.findById(tournamentId).orElseThrow(() -> new NoRequestedTournamentException(tournamentId));

        List<Tournament> tournamentsWhereUserIsSignedUp = tournamentRepository.findAllByParticipants(user);
        if(tournamentsWhereUserIsSignedUp.contains(tournament)) throw new UserIsAlreadySignedUpException(user.getUsername());

        tournament.addParticipant(user);
        user.addTournament(tournament);

        tournamentRepository.save(tournament);
        applicationUserRepository.save(user);
    }

    @Transactional
    public void unsubscribeFromTournament(int tournamentId, String username) {
        ApplicationUser user = applicationUserRepository.getApplicationUserByUsername(username).orElseThrow(() -> new NoRequestedUserException(username));
        Tournament tournament = tournamentRepository.findById(tournamentId).orElseThrow(() -> new NoRequestedTournamentException(tournamentId));

        List<Tournament> tournamentsWhereUserIsSignedUp = tournamentRepository.findAllByParticipants(user);
        if(!tournamentsWhereUserIsSignedUp.contains(tournament)) throw new UserIsNotSignedUpException(user.getUsername());



        tournament.removeParticipant(user);
        user.removeTournament(tournament);

        tournamentRepository.save(tournament);
        applicationUserRepository.save(user);

    }

    @Transactional
    public void deleteOwnTournament(int tournamentId, String username) {
        ApplicationUser user = applicationUserRepository.getApplicationUserByUsername(username).orElseThrow(() -> new NoRequestedUserException(username));
        Tournament tournament = tournamentRepository.findById(tournamentId).orElseThrow(() -> new NoRequestedTournamentException(tournamentId));

        if(tournament.getHost().getUsername().equals(username)){

            tournamentRepository.delete(tournament);

        } else throw new NoRequestedPermissions("User " + username + "has no rights to delete tournament with id: " + tournamentId);

    }

    public List<ApplicationUser> manageOwnTournament(int tournamentId, String username) {

        ApplicationUser user = applicationUserRepository.getApplicationUserByUsername(username).orElseThrow(() -> new NoRequestedUserException(username));
        Tournament tournament = tournamentRepository.findById(tournamentId).orElseThrow(() -> new NoRequestedTournamentException(tournamentId));

        if(!tournament.getHost().getUsername().equals(username)) throw new NoRequestedPermissions("User " + username + "has no rights to manage tournament with id: " + tournamentId);

        return  tournament.getParticipants();
    }


    public Tournament findById(int tournamentId) {
        return tournamentRepository.findById(tournamentId).orElseThrow(() -> new NoRequestedTournamentException(tournamentId));
    }

    public void removeParticipantFromTournament(int tournamentId, int userId, String hostname) {

        ApplicationUser host = applicationUserRepository.getApplicationUserByUsername(hostname).orElseThrow(() -> new NoRequestedUserException(hostname));
        Tournament tournament = tournamentRepository.findById(tournamentId).orElseThrow(() -> new NoRequestedTournamentException(tournamentId));
        ApplicationUser participant = applicationUserRepository.findById(userId).orElseThrow(() -> new NoRequestedUserException(userId));

        if(!tournament.getHost().getUsername().equals(hostname)) throw new NoRequestedPermissions("User " + hostname + "has no rights to manage tournament with id: " + tournamentId);
        if(!tournament.getParticipants().contains(participant)) throw new userIsNotAParticipantOfTournamentException("User " + participant.getUsername() + "is not a participant of tournament with id: " + tournamentId);

        tournament.removeParticipant(participant);
        tournamentRepository.save(tournament);

    }
}
