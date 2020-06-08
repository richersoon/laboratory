package com.richersoon.laboratory.integration.resource;

import com.richersoon.laboratory.api.dto.PaginatedDto;
import com.richersoon.laboratory.api.dto.VirusDto;
import com.richersoon.laboratory.api.dto.VirusRequestDto;
import com.richersoon.laboratory.api.service.VirusService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Endpoint that expose viruses functionalities
 */
@RestController
@RequestMapping(VirusResource.ENDPOINT)
@Api(tags = "viruses")
public class VirusResource {

    public static final String ENDPOINT = "/viruses";

    @Autowired
    private VirusService virusService;

    /**
     * Create unique virus
     *
     * @param createRequestDto the create request
     * @return created virus
     */
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public VirusDto create(@Valid @RequestBody CreateVirusRequestDto createRequestDto) {
        VirusRequestDto requestDto = VirusRequestDto.builder()
                .name(createRequestDto.getName())
                .description(createRequestDto.getDescription())
                .build();
        return virusService.create(requestDto);
    }

    /**
     * Update virus
     *
     * @param updateVirusRequestDto the update request
     * @param name                  the name
     * @return updated virus
     */
    @PutMapping("/{name}")
    public VirusDto update(@RequestBody UpdateVirusRequestDto updateVirusRequestDto,
                           @PathVariable String name) {
        VirusRequestDto requestDto = VirusRequestDto.builder()
                .name(name)
                .description(updateVirusRequestDto.getDescription())
                .build();
        return virusService.update(requestDto);
    }

    /**
     * Delete virus
     *
     * @param name the name
     */
    @DeleteMapping("/{name}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String name) {
        virusService.delete(name);
    }

    /**
     * Get virus
     *
     * @return the virus
     */
    @GetMapping("/{name}")
    public VirusDto get(@PathVariable String name) {
        return virusService.get(name);
    }

    /**
     * Get viruses
     *
     * @return the viruses
     */
    @GetMapping
    public PaginatedDto<VirusDto> getAll() {
        return virusService.getAll();
    }
}
