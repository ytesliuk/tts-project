package com.javacourse.model.service;

/**
 * @author Yuliia Tesliuk
 */
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message){
        super(message);
    }


}
