package com.richersoon.laboratory.integration.resource;

import com.richersoon.laboratory.api.dto.SymptomDto;
import com.richersoon.laboratory.api.dto.SymptomRequestDto;
import com.richersoon.laboratory.api.service.SymptomService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Endpoint that expose symptom functionalities
 */
@RestController
@RequestMapping("/symptoms")
@Api(tags = "symptoms")
public class SymptomResource {

    @Autowired
    private SymptomService symptomService;

    /**
     * Update symptom
     *
     * @param updateSymptomRequestDto the update request
     * @param id                      the symptom id
     * @return updated symptom
     */
    @PutMapping("/{id}")
    public SymptomDto update(@RequestBody UpdateSymptomRequestDto updateSymptomRequestDto,
                             @PathVariable String id) {
        SymptomRequestDto requestDto = SymptomRequestDto.builder()
                .id(id)
                .description(updateSymptomRequestDto.getDescription())
                .build();
        return symptomService.update(requestDto);
    }

    /**
     * Delete symptom
     *
     * @param id the symptom
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        symptomService.delete(id);
    }

    /**
     * Get symptom
     *
     * @param id                      the symptom id
     * @return a symptom
     */
    @GetMapping("/{id}")
    public SymptomDto get(@PathVariable String id) {
        return symptomService.get(id);
    }
}
