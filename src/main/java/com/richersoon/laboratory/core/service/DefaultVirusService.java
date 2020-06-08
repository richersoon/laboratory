package com.richersoon.laboratory.core.service;

import com.richersoon.laboratory.api.dto.VirusDto;
import com.richersoon.laboratory.api.dto.VirusRequestDto;
import com.richersoon.laboratory.api.exception.AlreadyExistException;
import com.richersoon.laboratory.api.exception.NotFoundException;
import com.richersoon.laboratory.api.service.VirusService;
import com.richersoon.laboratory.core.model.Virus;
import com.richersoon.laboratory.core.repository.VirusRepository;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Default implementation of {@link VirusService}
 */
@Service
@RequiredArgsConstructor
public class DefaultVirusService implements VirusService {

    private final VirusRepository virusRepository;
    private final MapperFacade mapperFacade;

    @Override
    public VirusDto create(final VirusRequestDto requestDto) {
        if(virusRepository.findByName(requestDto.getName()).isPresent()) {
            throw new AlreadyExistException();
        }

        Virus virus = Virus.create(requestDto);
        return mapperFacade.map(virusRepository.save(virus), VirusDto.class);
    }

    @Override
    public VirusDto update(final VirusRequestDto requestDto) {
        Virus updatedVirus = virusRepository.findByName(requestDto.getName())
                .map(virus -> virus.update(requestDto))
                .orElseThrow(NotFoundException::new);
        return mapperFacade.map(virusRepository.save(updatedVirus), VirusDto.class);
    }

}
