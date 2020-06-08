package com.richersoon.laboratory.api.service;

import com.richersoon.laboratory.api.dto.SymptomDto;
import com.richersoon.laboratory.api.dto.SymptomRequestDto;
import com.richersoon.laboratory.api.exception.AlreadyExistException;

/**
 * Contract for Symptom Service API
 */
public interface SymptomService {

    /**
     * Create new symptom
     * @param requestDto
     * @throws AlreadyExistException if symptom exist by description
     * @return the symptom
     */
    SymptomDto create(SymptomRequestDto requestDto);
}
