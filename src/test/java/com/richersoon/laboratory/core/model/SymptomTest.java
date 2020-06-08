package com.richersoon.laboratory.core.model;

import com.richersoon.laboratory.api.dto.SymptomRequestDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SymptomTest {

    @Test
    public void createSuccessfully() {
        SymptomRequestDto expected = commonTestRequestSymptom();

        Symptom actual = Symptom.create(expected);
        assertEquals(expected.getDescription(), actual.getDescription());
        assertNotNull(actual.getCreatedAt());
        assertNotNull(actual.getUpdatedAt());
    }

    @Test
    public void updateSuccessfully() {
        SymptomRequestDto setupRequest = commonTestRequestSymptom();
        Symptom setUpVirus = Symptom.create(setupRequest);

        SymptomRequestDto expected = SymptomRequestDto.builder()
                .description("Mild Headache")
                .build();
        Symptom actual = setUpVirus.update(expected);

        assertEquals(expected.getDescription(), actual.getDescription());
        assertNotNull(actual.getCreatedAt());
        assertNotNull(actual.getUpdatedAt());
    }

    private SymptomRequestDto commonTestRequestSymptom() {
        return SymptomRequestDto.builder()
                .description("Headache")
                .build();
    }
}