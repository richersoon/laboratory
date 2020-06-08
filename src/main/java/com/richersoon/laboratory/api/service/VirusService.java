package com.richersoon.laboratory.api.service;

import com.richersoon.laboratory.api.dto.PaginatedDto;
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
     * Delete a virus
     * @param name the virus name
     */
    void delete(String name);

    /**
     * Get a virus
     * @param name the name
     * @return the virus
     */
    VirusDto get(String name);

    /**
     * Get a viruses
     * @return the virus
     */
    PaginatedDto<VirusDto> getAll();
}
