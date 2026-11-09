package com.algoquest.challenge_service.service;

import com.algoquest.challenge_service.entity.Challenge;
import com.algoquest.challenge_service.entity.ChallengeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
public class ChallengeService {

    private final ChallengeRepository repository;

    public ChallengeService(ChallengeRepository repository) {
        this.repository = repository;
    }

    public List<Challenge> findAll(String theme, String difficulty) {
        if (theme != null) return repository.findByTheme(theme);
        if (difficulty != null) return repository.findByDifficulty(difficulty);
        return repository.findAll();
    }

    public Challenge findById(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Challenge introuvable"));
    }

    public Challenge create(Challenge challenge) {
        return repository.save(challenge);
    }

    public Challenge update(Long id, Challenge data) {
        Challenge existing = findById(id);
        existing.setTitle(data.getTitle());
        existing.setDescription(data.getDescription());
        existing.setTheme(data.getTheme());
        existing.setDifficulty(data.getDifficulty());
        existing.setPoints(data.getPoints());
        return repository.save(existing);
    }

    public void delete(Long id) {
        repository.delete(findById(id));
    }
}