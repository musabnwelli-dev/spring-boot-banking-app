package net.javaguides.banking.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.banking.dto.TransactionDto;
import net.javaguides.banking.dto.TransferFundDto;
import net.javaguides.banking.entity.Transaction;
import net.javaguides.banking.entity.TransactionType;
import net.javaguides.banking.exception.AccountException;
import net.javaguides.banking.map.Mapper;
import net.javaguides.banking.dto.AccountDto;
import net.javaguides.banking.entity.Account;
import net.javaguides.banking.repository.AccountRepository;
import net.javaguides.banking.repository.TransactionRepository;
import net.javaguides.banking.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Implements business logic for bank accounts and transactions.
 */
@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService
{

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;



    @Override
    public AccountDto addAccount(AccountDto accountDto)
    {
        Account account = Mapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        return Mapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(Long id)
    {
       Account account = accountRepository.
                findById(id).
                orElseThrow(()-> new AccountException("The account does not exist."));
        return Mapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposit(Long id, double amount)
    {
        Account account = accountRepository.
                findById(id).
                orElseThrow(()-> new AccountException("The account does not exist."));
        double total = account.getBalance() + amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);
        Transaction transaction = new Transaction();
        transaction.setAccountId(id);
        transaction.setAmount(amount);
        transaction.setTransactionType(TransactionType.DEPOSIT);
        transaction.setTimestamp(LocalDateTime.now());
        transactionRepository.save(transaction);

        return Mapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto withdraw(Long id, double amount)
    {
        Account account = accountRepository.
                findById(id).
                orElseThrow(()-> new AccountException("The account does not exist."));
        if (amount > account.getBalance())
        {
            throw new RuntimeException("The given amount is bigger than the balance.");
        }
        double total = account.getBalance() - amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);
        Transaction transaction = new Transaction();
        transaction.setAccountId(id);
        transaction.setAmount(amount);
        transaction.setTransactionType(TransactionType.WITHDRAW);
        transaction.setTimestamp(LocalDateTime.now());
        transactionRepository.save(transaction);
        return Mapper.mapToAccountDto(savedAccount);
    }

    @Override
    public List<AccountDto> getAllAccounts()
    {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map((account)-> Mapper.mapToAccountDto(account)).toList();
    }

    @Override
    public void deleteAccount(Long id)
    {
        Account existingAccount = accountRepository.
                findById(id).
                orElseThrow(()-> new AccountException("The account does not exist."));
        accountRepository.deleteById(id);

    }

    @Override
    public void transferFunds(TransferFundDto transferFundDto)
    {
        //retrieve the source account from which we send money
        Account fromAccount = accountRepository.findById(transferFundDto.fromAccountId()).
                orElseThrow(() -> new AccountException("The account does not exist.")
        );

        //retrieve the target account to which we send the money
        Account toAccount = accountRepository.findById(transferFundDto.toAccountId()).
                orElseThrow(() -> new AccountException("The account does not exist.")
                );

        //deposit the money from the source account
        if (fromAccount.getBalance() < transferFundDto.amount())
        {
            throw new RuntimeException("There is not enough money in the account.");
        }
        fromAccount.setBalance(fromAccount.getBalance() - transferFundDto.amount());

        //credit the money to the target account
        toAccount.setBalance(toAccount.getBalance() + transferFundDto.amount());

        //save the changes
        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        //save the transation
        Transaction transaction = new Transaction();
        transaction.setAccountId(transferFundDto.fromAccountId());
        transaction.setAmount(transferFundDto.amount());
        transaction.setTransactionType(TransactionType.TRANSFER);
        transaction.setTimestamp(LocalDateTime.now());
        transactionRepository.save(transaction);

    }

    @Override
    public List<TransactionDto> getTransactions(Long accountId)
    {
        List<Transaction> transactions = transactionRepository
                .findByAccountIdOrderByTimestampDesc(accountId);
        return transactions.stream().map((transaction) -> convertToTransactionDto(transaction))
                .toList();
    }

    @Override
    public double getBalance(Long id)
    {
        Account account = accountRepository.findById(id).
                orElseThrow(() -> new AccountException("The account does not exist."));

        return account.getBalance();
    }

    private TransactionDto convertToTransactionDto(Transaction transaction)
    {
        return new TransactionDto(transaction.getId(),
                transaction.getAccountId(),
                transaction.getAmount(),
                transaction.getTransactionType(),
                transaction.getTimestamp());
    }


}
