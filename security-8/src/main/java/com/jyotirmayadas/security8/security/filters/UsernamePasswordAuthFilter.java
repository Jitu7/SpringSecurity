package com.jyotirmayadas.security8.security.filters;

import com.jyotirmayadas.security8.security.authentications.OtpAuthentication;
import com.jyotirmayadas.security8.security.authentications.UsernamePasswordAuthentication;
import com.jyotirmayadas.security8.security.managers.TokenManager;
import com.jyotirmayadas.security8.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;


public class UsernamePasswordAuthFilter extends OncePerRequestFilter {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private OtpService otpService;

    @Autowired
    private TokenManager tokenManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        // Step 1: username & password
        // Step 2: username & otp

        var username = request.getHeader("username");
        var password = request.getHeader("password");
        var otp = request.getHeader("otp");

        if (otp == null) {
            // Step 1
            Authentication authentication = new UsernamePasswordAuthentication(username, password);
            Authentication authenticated = manager.authenticate(authentication);

            // we generate an OTP
            otpService.generateOtp(username);

        } else {
            // Step 2
            Authentication authentication = new OtpAuthentication(username, otp);
            Authentication authenticated = manager.authenticate(authentication);

            // we issue a token
            var token = UUID.randomUUID().toString();
            tokenManager.add(token);

            response.setHeader("Authorization", token);
        }

    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {

        System.out.println(request.getServletPath());
        return !request.getServletPath().equals("/login");
    }
}
