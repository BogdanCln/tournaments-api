package com.unibuc.tournaments.service;

import com.unibuc.tournaments.exception.GenericForbiddenException;
import com.unibuc.tournaments.exception.GenericNotCreatedException;
import com.unibuc.tournaments.exception.GenericNotFoundException;
import com.unibuc.tournaments.model.tournament.Bracket;
import com.unibuc.tournaments.repository.BracketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class BracketServiceImpl implements BracketService {
    private final BracketRepository bracketRepository;

    @Autowired
    public BracketServiceImpl(BracketRepository bracketRepository) {
        this.bracketRepository = bracketRepository;
    }

    @Override
    public List<Bracket> findAll() {
        List<Bracket> brackets = new LinkedList<>();
        bracketRepository.findAll().iterator().forEachRemaining(brackets::add);
        return brackets;
    }

    @Override
    public Bracket findById(Long id) {
        Optional<Bracket> bracketOptional = bracketRepository.findById(id);
        if (bracketOptional.isEmpty()) {
            throw new GenericNotFoundException(Bracket.class.getSimpleName());
        }
        return bracketOptional.get();
    }

    @Override
    public List<Bracket> findByTournamentId(Long tournamentId) {
        return bracketRepository.findByTournamentId(tournamentId);
    }

    @Override
    public Bracket save(Bracket bracket) {
        try {
            return this.bracketRepository.save(bracket);
        } catch (Exception e) {
            throw new GenericNotCreatedException(Bracket.class.getSimpleName());
        }
    }

    @Override
    public void deleteById(Long id) {
        if (!bracketRepository.existsById(id)) {
            throw new GenericNotFoundException(Bracket.class.getSimpleName());
        }

        try {
            bracketRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println("Failed to delete bracket " + id);
            e.printStackTrace();

            throw new GenericForbiddenException("Deleting this bracket is not permitted.");
        }
    }
}
