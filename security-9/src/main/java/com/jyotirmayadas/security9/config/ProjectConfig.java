package com.jyotirmayadas.security9.config;

import com.jyotirmayadas.security9.security.CustomCsrfTokenRepository;
import com.jyotirmayadas.security9.security.filter.CsrfTokenLoggerFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.csrf.CsrfFilter;

import java.util.List;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
//        http.csrf().disable();

        http.csrf(c -> {
           c.ignoringAntMatchers("/csrfdisabled/**");
//           c.csrfTokenRepository(customCsrfTokenRepository());
        });

        http.addFilterAfter(csrfTokenLoggerFilter(), CsrfFilter.class);
    }

    @Bean
    public CsrfTokenLoggerFilter csrfTokenLoggerFilter() {
        return new CsrfTokenLoggerFilter();
    }

    @Bean
    public CustomCsrfTokenRepository customCsrfTokenRepository() {
        return new CustomCsrfTokenRepository();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        var userDetailsManager = new InMemoryUserDetailsManager();
        var bill = User.withUsername("bill")
                .password("1234")
                .authorities(List.of(() -> "reading"))
                .build();
        userDetailsManager.createUser(bill);

        return userDetailsManager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}
