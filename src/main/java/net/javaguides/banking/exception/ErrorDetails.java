package net.javaguides.banking.exception;

import java.time.LocalDateTime;

/**
 * Represents error information returned by the REST API.
 */
public record ErrorDetails(
        LocalDateTime timestamp,
        String message,
        String details,
        String errorCode
) {
}
