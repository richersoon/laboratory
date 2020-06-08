package com.richersoon.laboratory.core.service;

import com.richersoon.laboratory.api.dto.VirusDto;
import com.richersoon.laboratory.api.dto.VirusRequestDto;
import com.richersoon.laboratory.api.service.VirusService;
import com.richersoon.laboratory.core.model.Virus;
import com.richersoon.laboratory.core.repository.VirusRepository;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultVirusService implements VirusService {

    private final VirusRepository virusRepository;
    private final MapperFacade mapperFacade;

    @Override
    public VirusDto create(final VirusRequestDto requestDto) {
        Virus virus = Virus.create(requestDto);
        return mapperFacade.map(virusRepository.save(virus), VirusDto.class);
    }

}
