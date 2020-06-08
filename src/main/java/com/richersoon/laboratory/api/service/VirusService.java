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

    /**
     * Update a virus
     * @param requestDto
     * @throws AlreadyExistException if virus exist by name
     * @return updated virus
     */
    VirusDto update(VirusRequestDto requestDto);

    /**
     * Get a virus
     * @param name the name
     * @return the virus
     */
    VirusDto get(String name);
}
