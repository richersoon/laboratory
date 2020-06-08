package com.richersoon.laboratory.api.service;

import com.richersoon.laboratory.api.dto.VirusDto;
import com.richersoon.laboratory.api.dto.VirusRequestDto;

/**
 * Contract for Virus Service API
 */
public interface VirusService {
    VirusDto create(VirusRequestDto requestDto);
}
