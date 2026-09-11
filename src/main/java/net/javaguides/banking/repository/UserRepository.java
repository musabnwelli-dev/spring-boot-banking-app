package net.javaguides.banking.repository;

import net.javaguides.banking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * Provides database operations and lookup methods for users.
 */
public interface UserRepository extends JpaRepository<User, Long>
{
    /**
     * Finds a user by username.
     * @param username the username
     * @return the matching user, if present
     */
    Optional<User> findByUsername(String username);

    /**
     * Checks whether a user with the given email exists.
     * @param email the email address
     * @return true if the email exists, otherwise false
     */
    boolean existsByEmail(String email);

    /**
     * Finds a user by username or email.
     * @param username the username
     * @param email the email address
     * @return the matching user, if present
     */
    Optional<User> findByUsernameOrEmail(String username, String email);

    /**
     * Checks whether a user with the given username exists.
     * @param username the username
     * @return true if the username exists, otherwise false
     */
    boolean existsByUsername(String username);
}
