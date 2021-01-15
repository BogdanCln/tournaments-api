package com.unibuc.tournaments.service;

import com.unibuc.tournaments.exception.GenericNotCreatedException;
import com.unibuc.tournaments.exception.GenericNotFoundException;
import com.unibuc.tournaments.model.tournament.Bracket;
import com.unibuc.tournaments.model.tournament.Match;
import com.unibuc.tournaments.model.tournament.MatchStatus;
import com.unibuc.tournaments.model.tournament.Tournament;
import com.unibuc.tournaments.repository.BracketRepository;
import com.unibuc.tournaments.repository.MatchRepository;
import com.unibuc.tournaments.repository.TournamentRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TournamentService {
    TournamentRepository tournamentRepository;
    BracketRepository bracketRepository;
    MatchRepository matchRepository;

    public TournamentService(TournamentRepository tournamentRepository, BracketRepository bracketRepository, MatchRepository matchRepository) {
        this.tournamentRepository = tournamentRepository;
        this.bracketRepository = bracketRepository;
        this.matchRepository = matchRepository;
    }

    private boolean isPowerOfTwo(int number) {
        return number > 0 && ((number & (number - 1)) == 0);
    }

    public Tournament createTournament(Tournament tournament) {
        if (!isPowerOfTwo(tournament.getTeams().size())) {
            throw new GenericNotCreatedException(Tournament.class.getSimpleName());
        }

        Optional<Tournament> tournamentOptional;
        try {
            tournamentOptional = this.tournamentRepository.createTournament(tournament);
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            throw new GenericNotCreatedException(Tournament.class.getSimpleName());
        }
        if (tournamentOptional.isPresent()) {
            Tournament tournamentCreated = tournamentOptional.get();

            try {
                populateTournamentTeams(tournamentCreated.getId(), tournament.getTeams());
                // Updated with teams
                tournamentCreated = this.tournamentRepository.getTournament(tournamentCreated.getId()).get();
            } catch (Exception e) {
                e.printStackTrace();

                // Clean up: Delete created tournament since bracket initiation failed
                System.out.println("populateTournamentTeams() failed for new tournament - deleting tournament");
                tournamentRepository.deleteTournament(tournamentCreated.getId());

                throw new GenericNotCreatedException(Tournament.class.getSimpleName());
            }

            try {
                generateInitialBracket(tournamentCreated);
            } catch (Exception e) {
                e.printStackTrace();

                // Clean up: Delete created tournament since bracket initiation failed
                System.out.println("generateInitialBracket() failed for new tournament - deleting tournament");
                tournamentRepository.deleteTournament(tournamentCreated.getId());

                throw new GenericNotCreatedException(Tournament.class.getSimpleName());
            }

            // Updated with teams list
            return getTournament(tournamentCreated.getId());
        } else {
            throw new GenericNotCreatedException(Tournament.class.getSimpleName());
        }
    }

    private void populateTournamentTeams(int id, List<Integer> teams) {
        for (int teamId : teams) {
            tournamentRepository.createTournamentTeam(id, teamId);
        }
    }

    public Tournament getTournament(int id) {
        Optional<Tournament> tournamentOptional = tournamentRepository.getTournament(id);
        if (tournamentOptional.isPresent()) {
            return tournamentOptional.get();
        } else {
            throw new GenericNotFoundException(Tournament.class.getSimpleName());
        }
    }

    public void generateInitialBracket(Tournament tournament) {
        int teamsNo = tournament.getTeams().size();

        String firstPhase = "";
        if (teamsNo > 8) {
            firstPhase = "roundOf" + teamsNo;
        } else if (teamsNo == 8) {
            firstPhase = "quarterFinals";
        } else if (teamsNo == 4) {
            firstPhase = "semiFinals";
        } else if (teamsNo == 2) {
            firstPhase = "grandFinal";
        }

        Optional<Bracket> bracket = bracketRepository.createBracket(new Bracket(tournament.getId()));

        generateInitialMatchups(
                tournament.getTeams(),
                bracket.get().getId(),
                firstPhase,
                1
        );
    }

    /**
     * Generates a list of random matches to be set on a bracket phase.
     * Useful only for the first round of a Single Elimination bracket.
     * The following rounds should have matches generated based on previous round results.
     */
    private List<Match> generateInitialMatchups(
            List<Integer> teams,
            int bracketId,
            String firstPhase,
            int bestOf) {
        List<Match> matches = new ArrayList<>();
        for (int i = 0; i < teams.size(); i += 2) {
            Match matchToCreate = new Match(
                    0,
                    bracketId,
                    firstPhase,
                    teams.get(i),
                    teams.get(i + 1),
                    bestOf,
                    0,
                    0,
                    MatchStatus.NONE,
                    null,
                    false
            );
            Optional<Match> match = matchRepository.createMatch(matchToCreate);
            matches.add(match.get());
        }

        return matches;
    }

    public List<Tournament> getTournamentsFiltered(String name, Integer gameId) {
        return tournamentRepository.getTournamentFiltered(name, gameId);
    }

    public Bracket getTournamentBracket(int tournamentId) {
        return bracketRepository.getBracketByTournamentId(tournamentId);
    }
}
