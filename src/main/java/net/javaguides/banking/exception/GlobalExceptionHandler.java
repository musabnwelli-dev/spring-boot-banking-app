package net.javaguides.banking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

/**
 * Handles application exceptions and converts them into REST API error responses.
 */
@ControllerAdvice
public class GlobalExceptionHandler
{

    /**
     * Handles account-related exceptions.
     * @param accountException the thrown account exception
     * @param webRequest the current web request
     * @return the error response
     */
    @ExceptionHandler(AccountException.class)
    public ResponseEntity<ErrorDetails> handleAccountException(
            AccountException accountException,
            WebRequest webRequest
    )
    {
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                accountException.getMessage(),
                webRequest.getDescription(false),
                "ACCOUNT_NOT_FOUND"
        );

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles unexpected application exceptions.
     * @param exception the thrown exception
     * @param webRequest the current web request
     * @return the error response
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGenericException(
            Exception exception,
            WebRequest webRequest
    )
    {
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false),
                "INTERNAL_SERVER_ERROR"
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handles authentication and registration exceptions.
     * @param e the authentication API exception
     * @param webRequest the current web request
     * @return the error response
     */
    @ExceptionHandler(AuthApiException.class)
    public ResponseEntity<ErrorDetails> handleAuthApiException(AuthApiException e,
                                                                WebRequest webRequest)
    {
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                e.getMessage(),
                webRequest.getDescription(false),
                "ALREADY_EXISTS"

        );
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);

    }

}
