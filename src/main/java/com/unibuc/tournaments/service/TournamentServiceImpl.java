package com.unibuc.tournaments.service;

import com.unibuc.tournaments.exception.GenericForbiddenException;
import com.unibuc.tournaments.exception.GenericNotCreatedException;
import com.unibuc.tournaments.exception.GenericNotFoundException;
import com.unibuc.tournaments.model.tournament.Tournament;
import com.unibuc.tournaments.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class TournamentServiceImpl implements TournamentService {
    private final TournamentRepository tournamentRepository;

    @Autowired
    public TournamentServiceImpl(TournamentRepository tournamentRepository) {
        this.tournamentRepository = tournamentRepository;
    }

    @Override
    public List<Tournament> findAll() {
        List<Tournament> tournaments = new LinkedList<>();
        tournamentRepository.findAll().iterator().forEachRemaining(tournaments::add);
        return tournaments;
    }

    @Override
    public Tournament findById(Long id) {
        Optional<Tournament> tournamentOptional = tournamentRepository.findById(id);
        if (tournamentOptional.isEmpty()) {
            throw new GenericNotFoundException(Tournament.class.getSimpleName());
        }
        return tournamentOptional.get();
    }

    @Override
    public Tournament save(Tournament tournament) {
        try {
            return this.tournamentRepository.save(tournament);
        } catch (Exception e) {
            throw new GenericNotCreatedException(Tournament.class.getSimpleName());
        }
    }

    @Override
    public void deleteById(Long id) {
        if (!tournamentRepository.existsById(id)) {
            throw new GenericNotFoundException(Tournament.class.getSimpleName());
        }

        try {
            tournamentRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println("Failed to delete tournament " + id);
            e.printStackTrace();

            throw new GenericForbiddenException("Deleting this tournament is not permitted.");
        }
    }
}
