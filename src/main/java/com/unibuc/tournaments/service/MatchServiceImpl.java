package com.unibuc.tournaments.service;

import com.unibuc.tournaments.exception.GenericForbiddenException;
import com.unibuc.tournaments.exception.GenericNotCreatedException;
import com.unibuc.tournaments.exception.GenericNotFoundException;
import com.unibuc.tournaments.model.tournament.Match;
import com.unibuc.tournaments.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class MatchServiceImpl implements MatchService {
    private final MatchRepository matchRepository;

    @Autowired
    public MatchServiceImpl(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    @Override
    public List<Match> findAll() {
        List<Match> matches = new LinkedList<>();
        matchRepository.findAll().iterator().forEachRemaining(matches::add);
        return matches;
    }

    @Override
    public Match findById(Long id) {
        Optional<Match> matchOptional = matchRepository.findById(id);
        if (matchOptional.isEmpty()) {
            throw new GenericNotFoundException(Match.class.getSimpleName());
        }
        return matchOptional.get();
    }

    @Override
    public List<Match> findByBracketId(Long bracketId) {
        return matchRepository.findByBracketId(bracketId);
    }

    @Override
    public Match save(Match match) {
        try {
            return this.matchRepository.save(match);
        } catch (Exception e) {
            throw new GenericNotCreatedException(Match.class.getSimpleName());
        }
    }

    @Override
    public void deleteById(Long id) {
        if (!matchRepository.existsById(id)) {
            throw new GenericNotFoundException(Match.class.getSimpleName());
        }

        try {
            matchRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println("Failed to delete match " + id);
            e.printStackTrace();

            throw new GenericForbiddenException("Deleting this match is not permitted.");
        }
    }
}
