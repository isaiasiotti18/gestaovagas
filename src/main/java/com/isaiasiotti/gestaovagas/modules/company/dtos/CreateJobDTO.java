package com.isaiasiotti.gestaovagas.modules.company.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class CreateJobDTO {
    private String description;
    private String benefits;
    private String level;
}
