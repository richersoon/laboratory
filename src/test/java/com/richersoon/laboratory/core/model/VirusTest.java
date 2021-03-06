package com.richersoon.laboratory.core.model;

import com.richersoon.laboratory.api.dto.VirusRequestDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class VirusTest {

    @Test
    public void createSuccessfully() {
        VirusRequestDto expected = commonTestRequestVirus();

        Virus actual = Virus.create(expected);
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getDescription(), actual.getDescription());
        assertNotNull(actual.getCreatedAt());
        assertNotNull(actual.getUpdatedAt());
    }

    @Test
    public void updateSuccessfully() {
        VirusRequestDto setupRequest = commonTestRequestVirus();
        Virus setUpVirus = Virus.create(setupRequest);

        VirusRequestDto expected = VirusRequestDto.builder().name("COVID20")
                .description("The COVID-20 pandemic, also known as the coronavirus pandemic, is an ongoing pandemic " +
                        "of coronavirus disease 2019 (COVID‑20)")
                .build();
        Virus actual = setUpVirus.update(expected);

        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getDescription(), actual.getDescription());
        assertNotNull(actual.getCreatedAt());
        assertNotNull(actual.getUpdatedAt());
    }

    private VirusRequestDto commonTestRequestVirus() {
        return VirusRequestDto.builder().name("COVID19")
                .description("The COVID-19 pandemic, also known as the coronavirus pandemic, is an ongoing pandemic " +
                        "of coronavirus disease 2019 (COVID‑19)")
                .build();
    }
}