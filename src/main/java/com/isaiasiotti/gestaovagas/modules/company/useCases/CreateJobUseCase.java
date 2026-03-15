package com.isaiasiotti.gestaovagas.modules.company.useCases;

import org.springframework.stereotype.Service;

import com.isaiasiotti.gestaovagas.modules.company.entities.JobEntity;
import com.isaiasiotti.gestaovagas.modules.company.repositories.JobRepository;

@Service
public class CreateJobUseCase {

    private JobRepository repository;

    public CreateJobUseCase(JobRepository repository) {
        this.repository = repository;
    }

    public JobEntity execute(JobEntity jobEntity) {
        return repository.save(jobEntity);
    }
}
