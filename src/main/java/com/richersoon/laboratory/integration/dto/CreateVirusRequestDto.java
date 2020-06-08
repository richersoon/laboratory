package com.richersoon.laboratory.integration.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Create virus request
 */
@Data
public class CreateVirusRequestDto {
    @NotBlank
    private String name;

    @NotBlank
    private String description;
}
