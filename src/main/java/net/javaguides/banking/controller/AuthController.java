package net.javaguides.banking.controller;

import net.javaguides.banking.dto.LoginDto;
import net.javaguides.banking.dto.RegisterDto;
import net.javaguides.banking.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for user registration and authentication.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController
{
    @Autowired
    private AuthService authService;

    /**
     * Registers a new user.
     * @param registerDto the registration information
     * @return a registration confirmation message
     */
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto)
    {
        String response = authService.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * Authenticates a user.
     * @param loginDto the login credentials
     * @return a login confirmation message
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto)
    {
        String response = authService.login(loginDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
