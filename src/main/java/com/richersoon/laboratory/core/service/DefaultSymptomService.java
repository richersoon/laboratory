package com.richersoon.laboratory.core.service;

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
        if(symptomRepository.findByDescription(requestDto.getDescription()).isPresent()) {
            throw new AlreadyExistException();
        }

        Virus virus = virusRepository.findByName(requestDto.getVirusName())
                .orElseThrow(NotFoundException::new);

        Symptom symptom = Symptom.create(virus, requestDto);
        return mapperFacade.map(symptomRepository.save(symptom), SymptomDto.class);
    }
}