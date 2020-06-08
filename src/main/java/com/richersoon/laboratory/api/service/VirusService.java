package com.richersoon.laboratory.api.service;

import com.richersoon.laboratory.api.dto.VirusDto;
import com.richersoon.laboratory.api.dto.VirusRequestDto;
import com.richersoon.laboratory.api.exception.AlreadyExistException;

/**
 * Contract for Virus Service API
 */
public interface VirusService {

    /**
     * Create new virus
     * @param requestDto
     * @throws AlreadyExistException if virus exist by name
     * @return the virus
     */
    VirusDto create(VirusRequestDto requestDto);
}
