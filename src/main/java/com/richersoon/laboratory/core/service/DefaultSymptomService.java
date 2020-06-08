package com.richersoon.laboratory.core.service;

import com.richersoon.laboratory.api.dto.PaginatedDto;
import com.richersoon.laboratory.api.dto.SymptomDto;
import com.richersoon.laboratory.api.dto.SymptomRequestDto;
import com.richersoon.laboratory.api.exception.AlreadyExistException;
import com.richersoon.laboratory.api.exception.NotFoundException;
import com.richersoon.laboratory.api.service.SymptomService;
import com.richersoon.laboratory.core.model.Symptom;
import com.richersoon.laboratory.core.model.Virus;
import com.richersoon.laboratory.core.repository.SymptomRepository;
import com.richersoon.laboratory.core.repository.VirusRepository;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Default implementation of {@link SymptomService}
 */
@Service
@RequiredArgsConstructor
public class DefaultSymptomService implements SymptomService {

    private final SymptomRepository symptomRepository;
    private final VirusRepository virusRepository;
    private final MapperFacade mapperFacade;

    @Override
    public SymptomDto create(final SymptomRequestDto requestDto) {
        if (symptomRepository.findByDescription(requestDto.getDescription()).isPresent()) {
            throw new AlreadyExistException();
        }

        Virus virus = virusRepository.findByName(requestDto.getVirusName())
                .orElseThrow(NotFoundException::new);

        Symptom symptom = Symptom.create(virus, requestDto);
        return mapperFacade.map(symptomRepository.save(symptom), SymptomDto.class);
    }

    @Override
    public SymptomDto update(SymptomRequestDto requestDto) {
        return symptomRepository.findById(requestDto.getId())
                .map(symptom -> symptom.update(requestDto))
                .map(symptomRepository::save)
                .map(symptom -> mapperFacade.map(symptom, SymptomDto.class))
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public SymptomDto get(String name) {
        return symptomRepository.findById(name)
                .map(symptom -> mapperFacade.map(symptom, SymptomDto.class))
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public PaginatedDto<SymptomDto> getAll(String virusName) {
        List<SymptomDto> symptoms = mapperFacade.mapAsList(symptomRepository.findByVirusName(virusName), SymptomDto.class);
        PaginatedDto<SymptomDto> paginatedDto = new PaginatedDto<>(symptoms);
        return paginatedDto;
    }
}
