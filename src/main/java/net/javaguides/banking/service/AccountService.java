package net.javaguides.banking.service;

import net.javaguides.banking.dto.AccountDto;
import net.javaguides.banking.dto.TransactionDto;
import net.javaguides.banking.dto.TransferFundDto;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Defines business operations for bank accounts and transactions.
 */
@Service
public interface AccountService
{
    /**
     * Creates a new account.
     * @param accountDto the account data
     * @return the created account
     */
    AccountDto addAccount(AccountDto accountDto);

    /**
     * Retrieves an account by its ID.
     * @param id the account ID
     * @return the requested account
     */
    AccountDto getAccountById(Long id);

    /**
     * Deposits money into an account.
     * @param id the account ID
     * @param amount the amount to deposit
     * @return the updated account
     */
    AccountDto deposit(Long id, double amount);

    /**
     * Withdraws money from an account.
     * @param id the account ID
     * @param amount the amount to withdraw
     * @return the updated account
     */
    AccountDto withdraw(Long id, double amount);

    /**
     * Retrieves all accounts.
     * @return all available accounts
     */
    List<AccountDto> getAllAccounts();

    /**
     * Deletes an account.
     * @param id the account ID
     */
    void deleteAccount(Long id);

    /**
     * Transfers funds between two accounts.
     * @param transferFundDto the transfer information
     */
    void transferFunds(TransferFundDto transferFundDto);

    /**
     * Retrieves the transaction history of an account.
     * @param accountId the account ID
     * @return the account's transactions
     */
    List<TransactionDto> getTransactions(Long accountId);

    /**
     * Retrieves the balance of the account
     * @param id the account ID
     * @return teh account's balance
     */
    double getBalance(Long id);


}
