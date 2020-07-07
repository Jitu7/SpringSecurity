package com.example.security5.security.authentication;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/*
In real time need to extend Authentication(I)
and override all methods

Just for simplicity now we are extending UsernamePasswordAuthentication
 */
public class CustomAuthentication extends UsernamePasswordAuthenticationToken {


    public CustomAuthentication(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public CustomAuthentication(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }

}
