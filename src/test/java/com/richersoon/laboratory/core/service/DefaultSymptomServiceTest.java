package com.richersoon.laboratory.core.service;

import com.richersoon.laboratory.api.dto.PaginatedDto;
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

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
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

        assertNotNull(actual.getId());
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

    @Test
    public void updateSuccessfully() {
        Virus virus = commonTestVirus();
        SymptomRequestDto setUpRequest = commonTestRequestSymptom();
        Symptom setUpSymptom = Symptom.create(virus, setUpRequest);

        SymptomRequestDto expectedRequest = SymptomRequestDto.builder()
                .description("Mild Headache")
                .build();
        Symptom expected = setUpSymptom.update(expectedRequest);

        when(symptomRepository.findById(setUpRequest.getId())).thenReturn(Optional.of(setUpSymptom));
        when(symptomRepository.save(expected)).thenReturn(expected);

        SymptomDto actual = underTest.update(setUpRequest);
        verify(symptomRepository, times(1)).findById(setUpRequest.getId());
        verify(symptomRepository, times(1)).save(expected);

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getDescription(), actual.getDescription());
        assertEquals(expected.getCreatedAt(), actual.getCreatedAt());
        assertEquals(expected.getUpdatedAt(), actual.getUpdatedAt());
    }

    @Test
    public void updateShouldThrowNotFoundWhenIdNotFound() {
        SymptomRequestDto setUpRequest = commonTestRequestSymptom();

        when(symptomRepository.findById(setUpRequest.getId())).thenReturn(Optional.empty());

        NotFoundException actual = assertThrows(NotFoundException.class, () -> {
            underTest.update(setUpRequest);
        });

        verify(symptomRepository, times(1)).findById(setUpRequest.getId());
        verify(symptomRepository, times(0)).save(any());

        assertNotNull(actual);
        assertEquals(NotFoundException.MESSAGE, actual.getMessage());
    }

    @Test
    public void deleteSuccessfully() {
        Symptom mock = mock(Symptom.class);
        String any = any();
        when(symptomRepository.findById(any)).thenReturn(Optional.of(mock));
        doNothing().when(symptomRepository).deleteById(any);

        underTest.delete(any);
        verify(symptomRepository, times(1)).findById(any);
        verify(symptomRepository, times(1)).deleteById(any);
    }

    @Test
    public void deleteShouldThrowNotFoundExceptionWhenNameNotFound() {
        String any = any();

        when(symptomRepository.findById(any)).thenReturn(Optional.empty());

        NotFoundException actual = assertThrows(NotFoundException.class, () -> {
            underTest.delete(any);
        });
        verify(symptomRepository, times(1)).findById(any);
        verify(symptomRepository, times(0)).deleteById(any);
        assertNotNull(actual);
        assertEquals(NotFoundException.MESSAGE, actual.getMessage());
    }
    @Test
    public void getSuccessfully() {
        Virus virus = commonTestVirus();
        SymptomRequestDto setUpRequest = commonTestRequestSymptom();
        Symptom expected = Symptom.create(virus, setUpRequest);

        when(symptomRepository.findById(expected.getId())).thenReturn(Optional.of(expected));

        SymptomDto actual = underTest.get(expected.getId());
        verify(symptomRepository, times(1)).findById(expected.getId());

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getDescription(), actual.getDescription());
        assertEquals(expected.getCreatedAt(), actual.getCreatedAt());
        assertEquals(expected.getUpdatedAt(), actual.getUpdatedAt());
    }

    @Test
    public void getShouldThrowNotFoundWhenIdNotFound() {
        SymptomRequestDto setUpRequest = commonTestRequestSymptom();

        when(symptomRepository.findById(setUpRequest.getId())).thenReturn(Optional.empty());

        NotFoundException actual = assertThrows(NotFoundException.class, () -> {
            underTest.get(setUpRequest.getId());
        });

        verify(symptomRepository, times(1)).findById(setUpRequest.getId());

        assertNotNull(actual);
        assertEquals(NotFoundException.MESSAGE, actual.getMessage());
    }

    @Test
    public void getAllSuccessfully() {
        Virus virus = commonTestVirus();
        SymptomRequestDto setUpRequest = commonTestRequestSymptom();
        Symptom expected = Symptom.create(virus, setUpRequest);

        List<Symptom> expecteds = Arrays.asList(expected);

        when(symptomRepository.findByVirus_Name(virus.getName())).thenReturn(expecteds);

        PaginatedDto<SymptomDto> actuals = underTest.getAll(virus.getName());
        verify(symptomRepository, times(1)).findByVirus_Name(virus.getName());

        assertEquals(1, actuals.getItems().size());

        SymptomDto actual = actuals.getItems().iterator().next();
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getDescription(), actual.getDescription());
        assertEquals(expected.getCreatedAt(), actual.getCreatedAt());
        assertEquals(expected.getUpdatedAt(), actual.getUpdatedAt());
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
                .description("Headache")
                .build();

        Virus setUpVirus = Virus.create(requestDto);
        return setUpVirus;
    }
}