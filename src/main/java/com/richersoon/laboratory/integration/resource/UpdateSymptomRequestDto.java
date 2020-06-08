package com.richersoon.laboratory.integration.resource;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Update symptom request
 */
@Data
public class UpdateSymptomRequestDto {

    @NotBlank
    private String description;
}
