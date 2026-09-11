package net.javaguides.banking.controller;

import net.javaguides.banking.dto.AccountDto;
import net.javaguides.banking.dto.TransactionDto;
import net.javaguides.banking.dto.TransferFundDto;
import net.javaguides.banking.entity.Account;
import net.javaguides.banking.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * REST controller for managing bank accounts and account operations.
 */
@RestController
@RequestMapping("/api/accounts")
public class AccountController
{
    private AccountService accountService;

    /**
     * Creates an account controller with the required account service.
     * @param accountService the service used for account operations
     */
    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * Creates a new bank account.
     * @param accountDto the account data
     * @return the created account
     */
    //add Account REST API
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto)
    {
        AccountDto createdAccount = accountService.addAccount(accountDto);
        return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
    }

    /**
     * Retrieves an account by its ID.
     * @param id the account ID
     * @return the requested account
     */
    //get Account by Id REST API
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccount(@PathVariable Long id)
    {
        AccountDto accountDto = accountService.getAccountById(id);
        return new ResponseEntity<>(accountDto, HttpStatus.OK);
    }

    /**
     * Deposits money into an account.
     * @param id the account ID
     * @param amount the amount to deposit
     * @return the updated account
     */
    //deposit REST API
    @PreAuthorize("hasRole('USER')")
    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> deposit(@PathVariable Long id,
                                              @RequestBody Map<String, Double> amount)
    {
        double addedAmount = amount.get("amount");
        AccountDto accountDto = accountService.deposit(id, addedAmount);
        return new ResponseEntity<>(accountDto, HttpStatus.OK);
    }

    /**
     * Withdraws money from an account.
     * @param id the account ID
     * @param request the withdrawal amount
     * @return the updated account
     */
    //withdraw REST API
    @PreAuthorize("hasRole('USER')")
    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto> withdraw(@PathVariable Long id,
                                               @RequestBody Map<String, Double> request)
    {
        double amount = request.get("amount");
        AccountDto accountDto = accountService.withdraw(id, amount);
        return new ResponseEntity<>(accountDto, HttpStatus.OK);
    }

    /**
     * Retrieves all bank accounts.
     * @return the list of all accounts
     */
    //get all accounts REST API
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<AccountDto>> getAccounts()
    {
        List<AccountDto> accounts = accountService.getAllAccounts();
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    /**
     * Deletes an account by its ID.
     * @param id the account ID
     * @return a confirmation message
     */
    //delete account Rest API
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id)
    {
        accountService.deleteAccount(id);
        return new ResponseEntity<>("Account is deleted successfully!", HttpStatus.OK);

    }

    /**
     * Transfers funds between two accounts.
     * @param transferFundDto the transfer information
     * @return a confirmation message
     */
    //transfer REST API
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(@RequestBody TransferFundDto transferFundDto)
    {
        accountService.transferFunds(transferFundDto);
        return new ResponseEntity<>("Transfer successful.", HttpStatus.OK);
    }

    /**
     * Retrieves the transaction history of an account.
     * @param accountId the account ID
     * @return the account's transactions
     */
    //Transaction history REST API
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/{id}/transactions")
    public ResponseEntity<List<TransactionDto>> getTransactions(@PathVariable("id") Long accountId)
    {
        List<TransactionDto> transactions = accountService.getTransactions(accountId);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }


}
