package com.richersoon.laboratory.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Common holder for API's that returning collections
 * @param <T> the class type
 */
@Getter
@Setter
@RequiredArgsConstructor
public class PaginatedDto<T> {

    /** The items. */
    private final Collection<T> items;
}
