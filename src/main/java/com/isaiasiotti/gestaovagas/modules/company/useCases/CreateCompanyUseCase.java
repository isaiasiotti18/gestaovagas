package com.isaiasiotti.gestaovagas.modules.company.useCases;

import org.springframework.stereotype.Service;

import com.isaiasiotti.gestaovagas.exceptions.UserFoundException;
import com.isaiasiotti.gestaovagas.modules.company.entities.CompanyEntity;
import com.isaiasiotti.gestaovagas.modules.company.repositories.CompanyRepository;

@Service
public class CreateCompanyUseCase {

    private CompanyRepository repository;

    public CreateCompanyUseCase(CompanyRepository repository) {
        this.repository = repository;
    }

    public CompanyEntity execute(CompanyEntity companyEntity) {
        repository.findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail())
                .ifPresent((user) -> {
                    throw new UserFoundException();
                });

        return repository.save(companyEntity);
    }
}
