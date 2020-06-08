package com.richersoon.laboratory.api.exception;

/**
 * Abstract class of all custom exception
 */
public abstract class LaboratoryException extends RuntimeException {

    public LaboratoryException(String message) {
        super(message);
    }
}
