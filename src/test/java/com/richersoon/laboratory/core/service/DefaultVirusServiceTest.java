package com.richersoon.laboratory.core.service;

import com.richersoon.laboratory.api.dto.VirusDto;
import com.richersoon.laboratory.api.dto.VirusRequestDto;
import com.richersoon.laboratory.api.exception.AlreadyExistException;
import com.richersoon.laboratory.api.service.VirusService;
import com.richersoon.laboratory.core.model.Virus;
import com.richersoon.laboratory.core.repository.VirusRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class DefaultVirusServiceTest {

    @Autowired
    private VirusService underTest;

    @MockBean
    private VirusRepository virusRepository;

    @Test
    public void createSuccessfully() {
        VirusRequestDto setUpRequest = commonTestRequestVirus();
        Virus expected = Virus.create(setUpRequest);
        when(virusRepository.findByName(setUpRequest.getName())).thenReturn(Optional.empty());
        when(virusRepository.save(any())).thenReturn(expected);

        VirusDto actual = underTest.create(setUpRequest);
        verify(virusRepository, times(1)).findByName(setUpRequest.getName());
        verify(virusRepository, times(1)).save(any());

        assertNotNull(actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getDescription(), actual.getDescription());
        assertNotNull(actual.getCreatedAt());
        assertNotNull(actual.getUpdatedAt());
    }

    @Test
    public void createShouldThrowAlreadyExistExceptionWhenNameAlreadyExist() {
        VirusRequestDto setUpRequest = commonTestRequestVirus();
        Virus setUpVirus = Virus.create(setUpRequest);

        when(virusRepository.findByName(setUpRequest.getName())).thenReturn(Optional.of(setUpVirus));

        AlreadyExistException actual = assertThrows(AlreadyExistException.class, () -> {
            underTest.create(setUpRequest);
        });

        assertNotNull(actual);
        assertEquals(AlreadyExistException.ALREADY_EXISTS_ENTITY, actual.getMessage());
    }

    private VirusRequestDto commonTestRequestVirus() {
        return VirusRequestDto.builder().name("COVID19")
                .description("The COVID-19 pandemic, also known as the coronavirus pandemic, is an ongoing pandemic " +
                        "of coronavirus disease 2019 (COVID‑19)")
                .build();
    }
}