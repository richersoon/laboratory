package com.richersoon.laboratory.integration.resource;

import com.richersoon.laboratory.api.dto.PaginatedDto;
import com.richersoon.laboratory.api.dto.SymptomDto;
import com.richersoon.laboratory.api.dto.SymptomRequestDto;
import com.richersoon.laboratory.api.service.SymptomService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Endpoint that expose symptom subresource of virus functionalities
 *
 * TODO: for some reason this is not working in swagger https://stackoverflow.com/a/57311688/3448799
 */
@RestController
@RequestMapping(VirusResource.ENDPOINT + "/{name}/symptoms")
@Api(tags = "symptoms")
public class VirusSymptomResource {

    @Autowired
    private SymptomService symptomService;

    /**
     * Create unique virus
     * @param createRequestDto the create request
     * @return created symptom
     */
    @PostMapping
    public ResponseEntity<SymptomDto> create(@Valid @RequestBody CreateSymptomRequestDto createRequestDto,
                                             @PathVariable String name) {
        SymptomRequestDto requestDto = SymptomRequestDto.builder()
                .virusName(name)
                .description(createRequestDto.getDescription())
                .build();
        return new ResponseEntity<>(symptomService.create(requestDto), HttpStatus.CREATED);
    }

    /**
     * Get symptoms by virus name
     * @param name the virus name
     * @return the symptoms
     */
    @GetMapping
    public PaginatedDto<SymptomDto> getAll(@PathVariable String name) {
        return symptomService.getAll(name);
    }
}
