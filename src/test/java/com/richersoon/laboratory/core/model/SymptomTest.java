package com.richersoon.laboratory.core.model;

import com.richersoon.laboratory.api.dto.SymptomRequestDto;
import com.richersoon.laboratory.api.dto.VirusRequestDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SymptomTest {

    @Test
    public void createSuccessfully() {
        Virus virus = commonTestVirus();

        SymptomRequestDto expected = commonTestRequestSymptom();
        Symptom actual = Symptom.create(virus, expected);
        assertEquals(expected.getDescription(), actual.getDescription());
        assertNotNull(actual.getCreatedAt());
        assertNotNull(actual.getUpdatedAt());

        assertEquals(virus.getName(), actual.getVirus().getName());
    }

    @Test
    public void updateSuccessfully() {
        Virus virus = commonTestVirus();

        SymptomRequestDto setupRequest = commonTestRequestSymptom();
        Symptom setUpVirus = Symptom.create(virus, setupRequest);

        SymptomRequestDto expected = SymptomRequestDto.builder()
                .description("Mild Headache")
                .build();
        Symptom actual = setUpVirus.update(expected);

        assertEquals(expected.getDescription(), actual.getDescription());
        assertNotNull(actual.getCreatedAt());
        assertNotNull(actual.getUpdatedAt());

        assertEquals(virus.getName(), actual.getVirus().getName());
    }

    private SymptomRequestDto commonTestRequestSymptom() {
        return SymptomRequestDto.builder()
                .description("Headache")
                .build();
    }

    private Virus commonTestVirus() {
        VirusRequestDto requestDto =  VirusRequestDto.builder().name("COVID19")
                .description("The COVID-19 pandemic, also known as the coronavirus pandemic, is an ongoing pandemic " +
                        "of coronavirus disease 2019 (COVID‑19)")
                .build();

        Virus setUpVirus = Virus.create(requestDto);
        return setUpVirus;
    }
}