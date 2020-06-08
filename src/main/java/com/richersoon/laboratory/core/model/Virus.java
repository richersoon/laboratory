package com.richersoon.laboratory.core.model;

import com.richersoon.laboratory.api.dto.VirusRequestDto;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Domain object for virus
 */
@Getter
public class Virus {
    private String id;
    private String name;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    /**
     * Instantiates
     * @param requestDto the create request
     * @return the new virus
     */
    public static Virus create(VirusRequestDto requestDto) {
        Virus virus = new Virus();
        virus.id = UUID.randomUUID().toString();
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
    public void update(VirusRequestDto requestDto) {
        this.name = requestDto.getName();
        this.description = requestDto.getDescription();
        this.updatedAt = LocalDateTime.now();
    }
}
