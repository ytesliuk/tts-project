package com.javacourse.model.service;

/**
 * @author Yuliia Tesliuk
 */
public class AccessForbiddenException extends RuntimeException {
    public AccessForbiddenException(String message) {
        super(message);
    }
}
