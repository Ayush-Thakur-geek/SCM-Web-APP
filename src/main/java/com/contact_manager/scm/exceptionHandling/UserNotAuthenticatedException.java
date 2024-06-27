package com.contact_manager.scm.exceptionHandling;

public class UserNotAuthenticatedException extends RuntimeException {
    public UserNotAuthenticatedException(String message) {
        super(message);
    }

    public UserNotAuthenticatedException() {
        super("User not authenticated.");
    }
}
