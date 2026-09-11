package net.javaguides.banking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Contains the information required to register a new user.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto
{
    private Long id;

    private String name;

    private String username;

    private String email;

    private String password;
}
