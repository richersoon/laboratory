package com.richersoon.laboratory.api.service;

import com.richersoon.laboratory.api.dto.SymptomDto;
import com.richersoon.laboratory.api.dto.SymptomRequestDto;
import com.richersoon.laboratory.api.exception.AlreadyExistException;
import com.richersoon.laboratory.api.exception.NotFoundException;

/**
 * Contract for Symptom Service API
 */
public interface SymptomService {

    /**
     * Create new symptom
     * @param requestDto
     * @throws AlreadyExistException if symptom exist by description
     * @throws NotFoundException if virus not found by name
     * @return the symptom
     */
    SymptomDto create(SymptomRequestDto requestDto);

    /**
     * Update symptom
     * @param requestDto
     * @throws NotFoundException if symptom not found by id
     * @return the updated symptom
     */
    SymptomDto update(SymptomRequestDto requestDto);
}
