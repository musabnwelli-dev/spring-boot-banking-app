package net.javaguides.banking.exception;


/**
 * Exception thrown when an account-related operation fails.
 */
public class AccountException extends RuntimeException
{

    /**
     * Creates an account exception with the specified message.
     * @param message the error message
     */
    public AccountException(String message)
    {
        super(message);
    }
}
