package com.unibuc.tournaments.service;

import com.unibuc.tournaments.exception.GenericForbiddenException;
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
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional()
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
                System.out.println("generateInitialBracket() failed for new tournament");

                // Clean up: Delete created tournament since bracket initiation failed
//                System.out.println("generateInitialBracket() failed for new tournament - deleting tournament");
//                tournamentRepository.deleteTournament(tournamentCreated.getId());

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
            firstPhase = "round_of_" + teamsNo;
        } else if (teamsNo == 8) {
            firstPhase = "quarter_finals";
        } else if (teamsNo == 4) {
            firstPhase = "semi_finals";
        } else if (teamsNo == 2) {
            firstPhase = "grand_final";
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

    public void deleteTournament(int id) {
        if (tournamentRepository.getTournament(id).isEmpty()) {
            throw new GenericNotFoundException(Tournament.class.getSimpleName());
        }

        try {
            tournamentRepository.deleteTournament(id);
        } catch (Exception e) {
            System.out.println("Failed to delete tournament " + id);
            e.printStackTrace();

            throw new GenericForbiddenException("Deleting this tournament is not permitted.");
        }
    }


    public Tournament updateTournament(int id, Tournament newTournament) {
        Optional<Tournament> existingTournament = tournamentRepository.getTournament(id);
        if (existingTournament.isEmpty()) {
            throw new GenericNotFoundException(Tournament.class.getSimpleName());
        }

        newTournament.setId(existingTournament.get().getId());

        if (newTournament.getStatus() == null) {
            newTournament.setStatus(existingTournament.get().getStatus());
        }
        if (newTournament.getStartDate() == null) {
            newTournament.setStartDate(existingTournament.get().getStartDate());
        }
        if (newTournament.getEndDate() == null) {
            newTournament.setEndDate(existingTournament.get().getEndDate());
        }
        if (newTournament.getLocation() == null) {
            newTournament.setLocation(existingTournament.get().getLocation());
        }

        Optional<Tournament> tournamentOptional = this.tournamentRepository.updateTournament(newTournament);
        if (tournamentOptional.isPresent()) {
            return tournamentOptional.get();
        } else {
            throw new GenericNotCreatedException(Tournament.class.getSimpleName());
        }
    }

    public Match updateMatch(int id, Match newMatch) {
        Optional<Match> existingMatch = matchRepository.getMatch(id);
        if (existingMatch.isEmpty()) {
            throw new GenericNotFoundException(Match.class.getSimpleName());
        }

        newMatch.setId(existingMatch.get().getId());

        if (newMatch.getBestOf() == null) {
            newMatch.setBestOf(existingMatch.get().getBestOf());
        }
        if (newMatch.getRedTeamScore() == null) {
            newMatch.setRedTeamScore(existingMatch.get().getRedTeamScore());
        }
        if (newMatch.getBlueTeamScore() == null) {
            newMatch.setBlueTeamScore(existingMatch.get().getBlueTeamScore());
        }
        if (newMatch.getStatus() == null) {
            newMatch.setStatus(existingMatch.get().getStatus());
        }
        if (newMatch.getScheduledDate() == null) {
            newMatch.setScheduledDate(existingMatch.get().getScheduledDate());
        }
        if (newMatch.getDefaultWin() == null) {
            newMatch.setDefaultWin(existingMatch.get().getDefaultWin());
        }

        Optional<Match> matchOptional = this.matchRepository.updateMatch(newMatch);

//        String phaseName = newMatch.getBracketPhase();
//        if (phaseName != "grand_final") {
//            Boolean phaseEnded = isPhaseEnded(newMatch.getBracketId(), newMatch.getBracketPhase());
//            if (phaseEnded) {
        // Check if current phase is the most advanced one into the bracket
        // If true, generate next bracket phase
//            }
//        }

        if (matchOptional.isPresent()) {
            return matchOptional.get();
        } else {
            throw new GenericNotCreatedException(Match.class.getSimpleName());
        }
    }

    private Boolean isPhaseEnded(Integer bracketId, String bracketPhase) {
        Map<String, List<Match>> phases = bracketRepository.getBracketPhases(bracketId);
        for (Map.Entry<String, List<Match>> entry : phases.entrySet()) {
            if (entry.getKey() != bracketPhase) {
                for (Match match : entry.getValue()) {
                    if (match.getStatus() != MatchStatus.FINISHED) {
                        return false;
                    }
                }
            }
        }

        return true;
    }
}
