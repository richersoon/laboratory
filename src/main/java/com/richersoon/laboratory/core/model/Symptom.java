package com.richersoon.laboratory.core.model;

import com.richersoon.laboratory.api.dto.SymptomRequestDto;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Domain object for symptom
 */
@Getter
@EqualsAndHashCode(of = {"description"})
@Entity(name = Symptom.TABLE_NAME)
public class Symptom {

    static final String TABLE_NAME = "symptoms";

    @Id
    private String id;
    private String description;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    /**
     * Instantiates
     * @param requestDto the create request
     * @return the new symptom
     */
    public static Symptom create(SymptomRequestDto requestDto) {
        Symptom virus = new Symptom();
        virus.id = UUID.randomUUID().toString();
        virus.description = requestDto.getDescription();
        virus.createdAt = LocalDateTime.now();
        virus.updatedAt = LocalDateTime.now();
        return virus;
    }

}
