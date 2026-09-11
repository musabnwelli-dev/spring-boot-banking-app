package net.javaguides.banking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Represents account data transferred between application layers.
 */
@Data
@AllArgsConstructor
public class AccountDto
{
    private Long id;

    private String accountHolderName;

    private double balance;
}


