package net.javaguides.banking.dto;

import net.javaguides.banking.entity.TransactionType;

import java.time.LocalDateTime;

/**
 * Represents transaction data returned by the application.
 */
public record TransactionDto(
        Long id,
        Long accountId,
        double amount,
        TransactionType transactionType,
        LocalDateTime timestamp
) {
}
