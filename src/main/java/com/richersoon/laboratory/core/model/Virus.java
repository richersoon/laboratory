package com.richersoon.laboratory.core.model;

import com.richersoon.laboratory.api.dto.VirusRequestDto;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Domain object for virus
 */
@Getter
@EqualsAndHashCode(of = {"name"})
@Entity(name = Virus.TABLE_NAME)
public class Virus {

    static final String TABLE_NAME = "viruses";

    @Id
    private String name;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "virus")
    private List<Symptom> symptoms = new ArrayList<>();

    /**
     * Instantiates
     * @param requestDto the create request
     * @return the new virus
     */
    public static Virus create(VirusRequestDto requestDto) {
        Virus virus = new Virus();
        virus.name = requestDto.getName();
        virus.description = requestDto.getDescription();
        virus.createdAt = LocalDateTime.now();
        virus.updatedAt = LocalDateTime.now();
        return virus;
    }

    /**
     * Update
     * @param requestDto the create request
     * @return the updated virus
     */
    public Virus update(VirusRequestDto requestDto) {
        this.name = requestDto.getName();
        this.description = requestDto.getDescription();
        this.updatedAt = LocalDateTime.now();
        return this;
    }
}
