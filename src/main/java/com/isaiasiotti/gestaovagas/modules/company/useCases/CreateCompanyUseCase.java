package com.isaiasiotti.gestaovagas.modules.company.useCases;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.isaiasiotti.gestaovagas.exceptions.UserFoundException;
import com.isaiasiotti.gestaovagas.modules.company.entities.CompanyEntity;
import com.isaiasiotti.gestaovagas.modules.company.repositories.CompanyRepository;

@Service
public class CreateCompanyUseCase {

    private CompanyRepository repository;

    private PasswordEncoder passwordEncoder;

    public CreateCompanyUseCase(CompanyRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public CompanyEntity execute(CompanyEntity companyEntity) {
        repository.findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail()).ifPresent((user) -> {
            throw new UserFoundException();
        });

        var password = passwordEncoder.encode(companyEntity.getPassword());

        companyEntity.setPassword(password);

        return repository.save(companyEntity);
    }
}
