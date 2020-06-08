package com.richersoon.laboratory.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception whenever to prevent redundancy by value
 */
@ResponseStatus(value = HttpStatus.CONFLICT)
public class AlreadyExistException extends LaboratoryException {
    public static final String MESSAGE = "Already exists entity";

    public AlreadyExistException() {
        super(MESSAGE);
    }
}
