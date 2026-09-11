package net.javaguides.banking.service.impl;

import net.javaguides.banking.dto.LoginDto;
import net.javaguides.banking.dto.RegisterDto;
import net.javaguides.banking.entity.Role;
import net.javaguides.banking.entity.User;
import net.javaguides.banking.exception.AuthApiException;
import net.javaguides.banking.repository.RoleRepository;
import net.javaguides.banking.repository.UserRepository;
import net.javaguides.banking.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Set;

/**
 * Implements user registration and authentication logic.
 */
@Service
public class AuthServiceImpl implements AuthService
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public String register(RegisterDto registerDto)
    {
        if (userRepository.existsByUsername(registerDto.getUsername()))
        {
            throw new AuthApiException(HttpStatus.BAD_REQUEST,"User already exists.");
        }

        if (userRepository.existsByEmail(registerDto.getEmail()))
        {
            throw new AuthApiException(HttpStatus.BAD_REQUEST, "Email already exists.");
        }

        User user = new User();
        user.setName(registerDto.getName());
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(encoder.encode(registerDto.getPassword()));
        Role role = roleRepository.findByName("ROLE_USER");
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        userRepository.save(user);

        return "User registered successufully.";
    }

    @Override
    public String login(LoginDto loginDto)
    {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(),
                loginDto.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "User logged-in successfully.";
    }
}
