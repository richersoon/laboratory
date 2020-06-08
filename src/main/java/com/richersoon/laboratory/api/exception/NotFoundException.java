package com.richersoon.laboratory.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception whenever to prevent redundancy by value
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends LaboratoryException {
    public static final String MESSAGE = "Not found";

    public NotFoundException() {
        super(MESSAGE);
    }
}
