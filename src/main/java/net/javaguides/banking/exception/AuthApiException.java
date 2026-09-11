package net.javaguides.banking.exception;

import org.springframework.http.HttpStatus;

/**
 * Exception thrown when an authentication or registration operation fails.
 */
public class AuthApiException extends RuntimeException
{
    private HttpStatus status;

    private String message;

    /**
     * Creates an authentication API exception.
     * @param status the HTTP status associated with the error
     * @param message the error message
     */
    public AuthApiException(HttpStatus status, String message)
    {
        super(message);
        this.status = status;
    }


}
