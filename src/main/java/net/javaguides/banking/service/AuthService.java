package net.javaguides.banking.service;

import net.javaguides.banking.dto.LoginDto;
import net.javaguides.banking.dto.RegisterDto;

/**
 * Defines operations for user registration and authentication.
 */
public interface AuthService
{
    /**
     * Registers a new user with the default user role.
     * @param registerDto the registration information
     * @return a registration confirmation message
     */
    String register(RegisterDto registerDto);

    /**
     * Authenticates a user using username or email and password.
     * @param loginDto the login credentials
     * @return a login confirmation message
     */
    String login(LoginDto loginDto);
}
