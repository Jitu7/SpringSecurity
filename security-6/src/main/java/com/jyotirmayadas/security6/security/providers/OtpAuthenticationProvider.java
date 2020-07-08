package com.jyotirmayadas.security6.security.providers;

import com.jyotirmayadas.security6.entities.Otp;
import com.jyotirmayadas.security6.reposiories.OtpRepository;
import com.jyotirmayadas.security6.security.authentications.OtpAuthentication;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class OtpAuthenticationProvider implements AuthenticationProvider {

    private final OtpRepository otpRepository;

    public OtpAuthenticationProvider(OtpRepository otpRepository) {
        this.otpRepository = otpRepository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String otp = String.class.cast(authentication.getCredentials());

        Optional<Otp> otpByUsername = otpRepository.findOtpByUsername(username);

        if (otpByUsername.isPresent()) {
            return new OtpAuthentication(username, otp, List.of(() -> "read"));
        }
        throw new BadCredentialsException("Bad Credentials :( ");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return OtpAuthentication.class.equals(authentication);
    }

}
