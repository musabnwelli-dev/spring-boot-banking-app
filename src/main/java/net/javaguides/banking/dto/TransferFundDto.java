package net.javaguides.banking.dto;

/**
 * Contains the information required to transfer funds between accounts.
 */
public record TransferFundDto(
        Long fromAccountId,
        Long toAccountId,
        double amount
) {
}
