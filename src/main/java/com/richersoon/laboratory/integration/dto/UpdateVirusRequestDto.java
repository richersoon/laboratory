package com.richersoon.laboratory.integration.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Update virus request
 */
@Data
public class UpdateVirusRequestDto {

    @NotBlank
    private String description;
}
