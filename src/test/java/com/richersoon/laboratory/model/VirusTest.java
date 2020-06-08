package com.richersoon.laboratory.model;

import com.richersoon.laboratory.VirusRequestDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VirusTest {

    @Test
    public void createSuccessfully() {
        VirusRequestDto expected = commonTestRequestVirus();

        Virus actual = Virus.create(expected);
        assertNotNull(actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getDescription(), actual.getDescription());
        assertNotNull(actual.getCreatedAt());
        assertNotNull(actual.getUpdatedAt());
    }

    @Test
    public void updateSuccessfully() {
        VirusRequestDto setupRequest = commonTestRequestVirus();
        Virus actual = Virus.create(setupRequest);

        VirusRequestDto expected = VirusRequestDto.builder().name("COVID20")
                .description("The COVID-20 pandemic, also known as the coronavirus pandemic, is an ongoing pandemic " +
                        "of coronavirus disease 2019 (COVID‑20)")
                .build();
        actual.update(expected);

        assertNotNull(actual.getId());
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