package com.richersoon.laboratory.api.exception;

/**
 * Exception whenever to prevent redundancy by value
 */
public class NotFoundException extends LaboratoryException {
    public static final String MESSAGE = "Not found";

    public NotFoundException() {
        super(MESSAGE);
    }
}
