package com.richersoon.laboratory.integration.resource;

import com.richersoon.laboratory.api.dto.PaginatedDto;
import com.richersoon.laboratory.api.dto.VirusDto;
import com.richersoon.laboratory.api.dto.VirusRequestDto;
import com.richersoon.laboratory.api.service.VirusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Endpoint that expose viruses functionalities
 */
@RestController("viruses")
public class VirusResource {

    @Autowired
    private VirusService virusService;

    /**
     * Create unique virus
     * @param createRequestDto the create request
     * @return created virus
     */
    @PostMapping
    public ResponseEntity<VirusDto> create(@RequestBody CreateVirusRequestDto createRequestDto) {
        VirusRequestDto requestDto = VirusRequestDto.builder()
                .name(createRequestDto.getName())
                .description(createRequestDto.getDescription())
                .build();
        return new ResponseEntity<>(virusService.create(requestDto), HttpStatus.CREATED);
    }

    /**
     * Get viruses
     * @return the viruses
     */
    @GetMapping
    public PaginatedDto<VirusDto> getAll() {
        return virusService.getAll();
    }
}
