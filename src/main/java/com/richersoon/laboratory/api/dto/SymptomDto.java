package com.richersoon.laboratory.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Symptom return object
 */
@Getter
@Setter
public class SymptomDto {
    private String description;
    private String virusName;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
