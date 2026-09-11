package net.javaguides.banking.security;

import net.javaguides.banking.entity.Role;
import net.javaguides.banking.entity.User;
import net.javaguides.banking.repository.RoleRepository;
import net.javaguides.banking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Loads application users from the database for Spring Security authentication.
 */
@Service
public class CustomUserDetailsService  implements UserDetailsService
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException
    {
        User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
        Set<GrantedAuthority> roles = user.getRoles().stream().map((role) ->
                new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());




        return new org.springframework.security.core.userdetails.User(
                usernameOrEmail,
                user.getPassword(),
                roles
        );
    }
}
