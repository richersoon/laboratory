package com.richersoon.laboratory.core.service;

import com.richersoon.laboratory.api.dto.PaginatedDto;
import com.richersoon.laboratory.api.dto.VirusDto;
import com.richersoon.laboratory.api.dto.VirusRequestDto;
import com.richersoon.laboratory.api.exception.AlreadyExistException;
import com.richersoon.laboratory.api.exception.NotFoundException;
import com.richersoon.laboratory.api.service.VirusService;
import com.richersoon.laboratory.core.model.Virus;
import com.richersoon.laboratory.core.repository.VirusRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
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
        when(virusRepository.save(expected)).thenReturn(expected);

        VirusDto actual = underTest.create(setUpRequest);
        verify(virusRepository, times(1)).findByName(setUpRequest.getName());
        verify(virusRepository, times(1)).save(expected);

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
        assertEquals(AlreadyExistException.MESSAGE, actual.getMessage());
    }

    @Test
    public void updateSuccessfully() {
        VirusRequestDto setUpRequest = commonTestRequestVirus();
        Virus setUpVirus = Virus.create(setUpRequest);

        VirusRequestDto expectedRequest = VirusRequestDto.builder()
                .name("COVID20")
                .description("The COVID-20 pandemic, also known as the coronavirus pandemic, is an ongoing pandemic " +
                        "of coronavirus disease 2019 (COVID‑20)")
                .build();
        Virus expected = setUpVirus.update(expectedRequest);

        when(virusRepository.findByName(setUpRequest.getName())).thenReturn(Optional.of(setUpVirus));
        when(virusRepository.save(expected)).thenReturn(expected);

        VirusDto actual = underTest.update(setUpRequest);
        verify(virusRepository, times(1)).findByName(setUpRequest.getName());
        verify(virusRepository, times(1)).save(expected);

        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getDescription(), actual.getDescription());
        assertEquals(expected.getCreatedAt(), actual.getCreatedAt());
        assertEquals(expected.getUpdatedAt(), actual.getUpdatedAt());
    }

    @Test
    public void updateShouldThrowNotFoundExceptionWhenNameNotFound() {
        VirusRequestDto setUpRequest = commonTestRequestVirus();
        when(virusRepository.findByName(setUpRequest.getName())).thenReturn(Optional.empty());

        NotFoundException actual = assertThrows(NotFoundException.class, () -> {
            underTest.update(setUpRequest);
        });
        assertNotNull(actual);
        assertEquals(NotFoundException.MESSAGE, actual.getMessage());
    }

    @Test
    public void deleteSuccessfully() {
        Virus mock = Mockito.mock(Virus.class);
        String any = any();
        when(virusRepository.findByName(any)).thenReturn(Optional.of(mock));
        doNothing().when(virusRepository).deleteById(any);

        underTest.delete(any);
        verify(virusRepository, times(1)).findByName(any);
        verify(virusRepository, times(1)).deleteById(any);
    }

    @Test
    public void deleteShouldThrowNotFoundExceptionWhenNameNotFound() {
        String any = any();

        when(virusRepository.findByName(any)).thenReturn(Optional.empty());

        NotFoundException actual = assertThrows(NotFoundException.class, () -> {
            underTest.delete(any);
        });
        verify(virusRepository, times(1)).findByName(any);
        verify(virusRepository, times(0)).deleteById(any);
        assertNotNull(actual);
        assertEquals(NotFoundException.MESSAGE, actual.getMessage());
    }

    @Test
    public void getSuccessfully() {
        VirusRequestDto setUpRequest = commonTestRequestVirus();
        Virus expected = Virus.create(setUpRequest);

        when(virusRepository.findByName(setUpRequest.getName())).thenReturn(Optional.of(expected));

        VirusDto actual = underTest.get(expected.getName());
        verify(virusRepository, times(1)).findByName(setUpRequest.getName());

        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getDescription(), actual.getDescription());
        assertEquals(expected.getCreatedAt(), actual.getCreatedAt());
        assertEquals(expected.getUpdatedAt(), actual.getUpdatedAt());
    }

    @Test
    public void getShouldThrowNotFoundExceptionWhenNameNotFound() {
        VirusRequestDto setUpRequest = commonTestRequestVirus();
        when(virusRepository.findByName(setUpRequest.getName())).thenReturn(Optional.empty());

        NotFoundException actual = assertThrows(NotFoundException.class, () -> {
            underTest.get(setUpRequest.getName());
        });
        assertNotNull(actual);
        assertEquals(NotFoundException.MESSAGE, actual.getMessage());
    }

    @Test
    public void getAllSuccessfully() {
        VirusRequestDto setUpRequest = commonTestRequestVirus();
        Virus expected = Virus.create(setUpRequest);
        List<Virus> expecteds = Arrays.asList(expected);

        when(virusRepository.findAll()).thenReturn(expecteds);

        PaginatedDto<VirusDto> actuals = underTest.getAll();
        verify(virusRepository, times(1)).findAll();

        assertEquals(1, actuals.getItems().size());

        VirusDto actual = actuals.getItems().iterator().next();
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getDescription(), actual.getDescription());
        assertEquals(expected.getCreatedAt(), actual.getCreatedAt());
        assertEquals(expected.getUpdatedAt(), actual.getUpdatedAt());
    }

    private VirusRequestDto commonTestRequestVirus() {
        return VirusRequestDto.builder().name("COVID19")
                .description("The COVID-19 pandemic, also known as the coronavirus pandemic, is an ongoing pandemic " +
                        "of coronavirus disease 2019 (COVID‑19)")
                .build();
    }
}