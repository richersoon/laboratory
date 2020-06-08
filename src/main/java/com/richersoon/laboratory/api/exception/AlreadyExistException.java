package com.richersoon.laboratory.api.exception;

/**
 * Exception whenever to prevent redundancy by value
 */
public class AlreadyExistException extends LaboratoryException {
    public static final String ALREADY_EXISTS_ENTITY = "Already exists entity";

    public AlreadyExistException() {
        super(ALREADY_EXISTS_ENTITY);
    }
}
