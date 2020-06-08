package com.richersoon.laboratory.integration.resource;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Create virus request
 */
@Data
public class UpdateVirusRequestDto {

    @NotBlank
    private String description;
}
