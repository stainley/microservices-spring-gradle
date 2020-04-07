package com.salapp.microservices.util.core.exceptions;

/**
 * @author Stainley Lebron
 * @since 4/7/20.
 */
public class NotFoundException extends RuntimeException{

    public NotFoundException(){

    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundException(Throwable cause) {
        super(cause);
    }
}
