package com.richersoon.laboratory.core.service;

import com.richersoon.laboratory.api.dto.SymptomDto;
import com.richersoon.laboratory.api.dto.SymptomRequestDto;
import com.richersoon.laboratory.api.dto.VirusRequestDto;
import com.richersoon.laboratory.api.exception.AlreadyExistException;
import com.richersoon.laboratory.api.exception.NotFoundException;
import com.richersoon.laboratory.api.service.SymptomService;
import com.richersoon.laboratory.core.model.Symptom;
import com.richersoon.laboratory.core.model.Virus;
import com.richersoon.laboratory.core.repository.SymptomRepository;
import com.richersoon.laboratory.core.repository.VirusRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class DefaultSymptomServiceTest {

    @Autowired
    private SymptomService underTest;

    @MockBean
    private SymptomRepository symptomRepository;

    @MockBean
    private VirusRepository virusRepository;

    @Test
    public void createSuccessfully() {
        Virus virus = commonTestVirus();
        SymptomRequestDto setUpRequest = commonTestRequestSymptom();
        Symptom expected = Symptom.create(virus, setUpRequest);

        when(virusRepository.findByName(virus.getName())).thenReturn(Optional.of(virus));
        when(symptomRepository.save(expected)).thenReturn(expected);

        SymptomDto actual = underTest.create(setUpRequest);
        verify(virusRepository, times(1)).findByName(virus.getName());
        verify(symptomRepository, times(1)).findByDescription(expected.getDescription());
        verify(symptomRepository, times(1)).save(expected);

        assertEquals(expected.getDescription(), actual.getDescription());
        assertNotNull(actual.getCreatedAt());
        assertNotNull(actual.getUpdatedAt());

        assertEquals(expected.getVirusName(), actual.getVirusName());
    }

    @Test
    public void createShouldThrowAlreadyExistExceptionWhenDescriptionAlreadyExist() {
        SymptomRequestDto setUpRequest = commonTestRequestSymptom();

        when(symptomRepository.findByDescription(any()))
                .thenReturn(Optional.of(mock(Symptom.class)));

        AlreadyExistException actual = assertThrows(AlreadyExistException.class, () -> {
            underTest.create(setUpRequest);
        });

        verify(symptomRepository, times(1)).findByDescription(any());
        verify(virusRepository, times(0)).findByName(any());

        assertNotNull(actual);
        assertEquals(AlreadyExistException.MESSAGE, actual.getMessage());
    }

    @Test
    public void createShouldThrowNotFoundWhenVirusNotFound() {
        SymptomRequestDto setUpRequest = commonTestRequestSymptom();

        when(symptomRepository.findByDescription(any()))
                .thenReturn(Optional.empty());
        when(virusRepository.findByName(any()))
                .thenReturn(Optional.empty());

        NotFoundException actual = assertThrows(NotFoundException.class, () -> {
            underTest.create(setUpRequest);
        });

        verify(symptomRepository, times(1)).findByDescription(any());
        verify(virusRepository, times(1)).findByName(any());

        assertNotNull(actual);
        assertEquals(NotFoundException.MESSAGE, actual.getMessage());
    }

    private SymptomRequestDto commonTestRequestSymptom() {
        Virus virus = commonTestVirus();
        return SymptomRequestDto.builder()
                .description("Headache")
                .virusName(virus.getName())
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