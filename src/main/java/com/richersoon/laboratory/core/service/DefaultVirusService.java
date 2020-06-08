package com.richersoon.laboratory.core.service;

import com.richersoon.laboratory.api.dto.PaginatedDto;
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

import java.util.List;

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
        if (virusRepository.findByName(requestDto.getName()).isPresent()) {
            throw new AlreadyExistException();
        }

        Virus virus = Virus.create(requestDto);
        return mapperFacade.map(virusRepository.save(virus), VirusDto.class);
    }

    @Override
    public VirusDto update(final VirusRequestDto requestDto) {
        return virusRepository.findByName(requestDto.getName())
                .map(virus -> virus.update(requestDto))
                .map(virusRepository::save)
                .map(virus -> mapperFacade.map(virus, VirusDto.class))
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public void delete(String name) {
        Virus virus = virusRepository.findByName(name)
                .orElseThrow(NotFoundException::new);

        virusRepository.deleteById(virus.getName());
    }

    @Override
    public VirusDto get(String name) {
        return virusRepository.findByName(name)
                .map(virus -> mapperFacade.map(virus, VirusDto.class))
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public PaginatedDto<VirusDto> getAll() {
        List<VirusDto> viruses = mapperFacade.mapAsList(virusRepository.findAll(), VirusDto.class);
        PaginatedDto<VirusDto> paginatedDto = new PaginatedDto<>(viruses);
        return paginatedDto;
    }

}
