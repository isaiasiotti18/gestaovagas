package com.isaiasiotti.gestaovagas.modules.candidate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isaiasiotti.gestaovagas.modules.candidate.CandidateEntity;
import com.isaiasiotti.gestaovagas.modules.candidate.CandidateRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CandidateRepository repository;

    @PostMapping
    public CandidateEntity create(@Valid @RequestBody CandidateEntity candidateEntity) {
        return repository.save(candidateEntity);
    }
}
