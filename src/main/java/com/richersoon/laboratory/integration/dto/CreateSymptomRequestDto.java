package com.richersoon.laboratory.integration.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Create symptom request
 */
@Data
public class CreateSymptomRequestDto {
    @NotBlank
    private String description;
}
