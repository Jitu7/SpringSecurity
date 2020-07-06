package com.jyotirmayadas.security4.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public CustomAuthenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        // here you implement the authentication logic
        // if the request is authenticate you should return here
        // an fully authenticated Authentication instance

        // if the request is not authenticated you should throw AuthenticationException

        // if the Authentication isn't supported by this AP -> return null

        var username = authentication.getName();
        var password = String.valueOf(authentication.getCredentials());

        var userDetails = userDetailsService.loadUserByUsername(username);

        if (userDetails != null) {
            if (passwordEncoder.matches(password, userDetails.getPassword())) {
                var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username,
                        password,
                        userDetails.getAuthorities());

                return usernamePasswordAuthenticationToken;
            }
        }
        throw new BadCredentialsException("Error!");
    }

    /*
    supports() is called by the AuthenticationManger
    before it call the authenticate()
     */
    @Override
    public boolean supports(Class<?> authentication) { // type of Authentication
        return UsernamePasswordAuthenticationToken.class.equals(authentication);
    }
}
