package net.javaguides.banking.map;

import net.javaguides.banking.dto.AccountDto;
import net.javaguides.banking.entity.Account;

/**
 * Provides mapping methods between account entities and DTOs.
 */
public class Mapper
{

    /**
     * Converts an account DTO into an account entity.
     * @param accountDto the account DTO
     * @return the mapped account entity
     */
    public static Account mapToAccount(AccountDto accountDto)
    {
        Account account = new Account(
                accountDto.getId(),
                accountDto.getAccountHolderName(),
                accountDto.getBalance()
                );
        return account;
    }

    /**
     * Converts an account entity into an account DTO.
     * @param account the account entity
     * @return the mapped account DTO
     */
    public static AccountDto mapToAccountDto(Account account)
    {
        AccountDto accountDto = new AccountDto(
                account.getId(),
                account.getAccountHolderName(),
                account.getBalance()
        );
        return accountDto;
    }
}
