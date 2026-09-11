package net.javaguides.banking.repository;

import net.javaguides.banking.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * Provides database operations for transactions.
 */
public interface TransactionRepository extends JpaRepository<Transaction, Long>
{
    /**
     * Finds all transactions for an account ordered by newest first.
     * @param accountId the account ID
     * @return the account's transactions
     */
    List<Transaction> findByAccountIdOrderByTimestampDesc(Long accountId);
}
