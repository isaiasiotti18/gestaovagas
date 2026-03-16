package com.isaiasiotti.gestaovagas.modules.company.useCases;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;

import java.time.Duration;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.isaiasiotti.gestaovagas.modules.company.dtos.AuthCompanyDTO;
import com.isaiasiotti.gestaovagas.modules.company.repositories.CompanyRepository;

@Service
public class AuthCompanyUseCase {

    @Value("${security.token.secret}")
    private String secretKey;

    private CompanyRepository repository;

    private PasswordEncoder passwordEncoder;

    public AuthCompanyUseCase(CompanyRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public String execute(AuthCompanyDTO authCompanyDTO) throws AuthenticationException {
        var company = repository.findByUsername(authCompanyDTO.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Username or Password Incorrect."));

        // Verificar password
        var passwordMatches = passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());

        if (!passwordMatches) {
            throw new BadCredentialsException("Username or Password Incorrect.");
        }

        // Gerar token
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        var token = JWT.create().withIssuer("GESTAOVAGAS").withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
                .withSubject(company.getId().toString()).sign(algorithm);

        return token;

    }
}
