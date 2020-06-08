package com.richersoon.laboratory.core.model;

import com.richersoon.laboratory.api.dto.SymptomRequestDto;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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

    @ManyToOne
    private Virus virus;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    /**
     * Instantiates
     * @param requestDto the create request
     * @return the new symptom
     */
    public static Symptom create(final Virus virus,
                                 final SymptomRequestDto requestDto) {
        Symptom symptom = new Symptom();
        symptom.virus = virus;
        symptom.id = UUID.randomUUID().toString();
        symptom.description = requestDto.getDescription();
        symptom.createdAt = LocalDateTime.now();
        symptom.updatedAt = LocalDateTime.now();
        return symptom;
    }

    /**
     * Update
     * @param requestDto the update request
     * @return the updated symptom
     */
    public Symptom update(final SymptomRequestDto requestDto) {
        this.description = requestDto.getDescription();
        this.updatedAt = LocalDateTime.now();
        return this;
    }

    /**
     * Get virus name
     * @return the virus name
     */
    public String getVirusName() {
        return this.virus.getName();
    }

}
