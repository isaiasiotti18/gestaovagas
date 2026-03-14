package com.isaiasiotti.gestaovagas.modules.candidate.useCases;

import org.springframework.stereotype.Service;

import com.isaiasiotti.gestaovagas.exceptions.UserFoundException;
import com.isaiasiotti.gestaovagas.modules.candidate.CandidateEntity;
import com.isaiasiotti.gestaovagas.modules.candidate.CandidateRepository;

@Service
public class CreateCandidateUseCase {

    private CandidateRepository repository;

    public CreateCandidateUseCase(CandidateRepository repository) {
        this.repository = repository;
    }

    public CandidateEntity execute(CandidateEntity candidate) {
        repository.findByUsernameOrEmail(candidate.getUsername(), candidate.getEmail())
                .ifPresent((user) -> {
                    throw new UserFoundException();
                });

        return repository.save(candidate);
    }
}
