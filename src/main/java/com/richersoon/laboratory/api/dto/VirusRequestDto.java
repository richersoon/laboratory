package com.richersoon.laboratory.api.dto;

import lombok.Builder;
import lombok.Getter;

/**
 * Contains any virus request that needs modification
 */
@Getter
@Builder
public class VirusRequestDto {
    private String name;
    private String description;
}
