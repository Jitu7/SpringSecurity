package com.jyotirmayadas.security9.security.filter;


import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CsrfTokenLoggerFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        Object csrf = request.getAttribute("_csrf");
        CsrfToken csrfToken = CsrfToken.class.cast(csrf);

        System.out.println(csrfToken.getToken());

        filterChain.doFilter(request, response);
    }

}
