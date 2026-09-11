package net.javaguides.banking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Contains the credentials required for user authentication.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto
{
    private String usernameOrEmail;

    private String password;
}
