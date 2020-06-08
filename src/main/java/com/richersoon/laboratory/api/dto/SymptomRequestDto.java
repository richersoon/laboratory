package com.richersoon.laboratory.api.dto;

import lombok.Builder;
import lombok.Getter;

/**
 * Contains any symptom request that needs modification
 */
@Getter
@Builder
public class SymptomRequestDto {
    private String virusName;
    private String description;
}
