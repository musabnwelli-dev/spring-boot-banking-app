package net.javaguides.banking.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configures Spring Security, authentication, and authorization.
 */
@Configuration
@EnableMethodSecurity
public class SpringSecurityConfig
{
    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * Creates the password encoder used to hash passwords.
     * @return the configured password encoder
     */
    @Bean
    public static PasswordEncoder passwordEncoder()
    {
        return  new BCryptPasswordEncoder();
    }


    /**
     * Configures HTTP security rules for the application.
     * @param http the HTTP security configuration
     * @return the configured security filter chain
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
    {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth ->
                        {
                            auth.requestMatchers("/api/auth/**").permitAll();
                            auth.anyRequest().authenticated();
                        })
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    /**
     * Creates the authentication manager used for user authentication.
     * @param configuration the authentication configuration
     * @return the authentication manager
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration)
    {
        return configuration.getAuthenticationManager();
    }
}
