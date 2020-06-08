package com.richersoon.laboratory;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class VirusRequestDto {
    private String name;
    private String description;
}
