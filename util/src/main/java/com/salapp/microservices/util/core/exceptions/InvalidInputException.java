package com.salapp.microservices.util.core.exceptions;

/**
 * @author Stainley Lebron
 * @since 4/7/20.
 */
public class InvalidInputException extends RuntimeException {

    public InvalidInputException() {
    }

    public InvalidInputException(String message) {
        super(message);
    }

    public InvalidInputException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidInputException(Throwable cause) {
        super(cause);
    }


}
