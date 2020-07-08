package com.jyotirmayadas.security6.service;

import com.jyotirmayadas.security6.entities.User;
import com.jyotirmayadas.security6.reposiories.UserRepository;
import com.jyotirmayadas.security6.security.model.SecurityUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public JpaUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = userRepository.findUserByUsername(username);
        User u = user.orElseThrow(() -> new UsernameNotFoundException("Not Found :( !!"));

        return new SecurityUser(u);
    }

}
