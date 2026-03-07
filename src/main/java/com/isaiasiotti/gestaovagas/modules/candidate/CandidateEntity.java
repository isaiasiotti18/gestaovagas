package com.isaiasiotti.gestaovagas.modules.candidate;

import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CandidateEntity {

    private UUID id;
    private String name;

    @Pattern(regexp = "\\S+", message = "Spaces are not allowed in username.")
    private String username;

    @Email(message = "Type a valid e-mail.")
    private String email;

    @Length(min = 10, max = 25, message = "The password must be between 10 and 25 characters long.")
    private String password;
    private String description;
    private String curriculum;

}
