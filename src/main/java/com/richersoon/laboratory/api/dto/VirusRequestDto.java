package com.richersoon.laboratory.api.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class VirusRequestDto {
    private String name;
    private String description;
}
