package com.algoquest.challenge_service.web;

import com.algoquest.challenge_service.entity.Challenge;
import com.algoquest.challenge_service.service.ChallengeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/challenges")
public class ChallengeController {

    private final ChallengeService service;

    public ChallengeController(ChallengeService service) {
        this.service = service;
    }

    @GetMapping
    public List<Challenge> list(@RequestParam(required = false) String theme,
                                @RequestParam(required = false) String difficulty) {
        return service.findAll(theme, difficulty);
    }

    @GetMapping("/{id}")
    public Challenge get(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Challenge create(@RequestBody Challenge challenge) {
        return service.create(challenge);
    }

    @PutMapping("/{id}")
    public Challenge update(@PathVariable Long id, @RequestBody Challenge challenge) {
        return service.update(id, challenge);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}