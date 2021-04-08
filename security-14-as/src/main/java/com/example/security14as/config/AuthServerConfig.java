package com.example.security14as.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
@RequiredArgsConstructor
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

  private final AuthenticationManager authenticationManager;
  private final UserDetailsService userDetailsService;

  @Override
  public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
    security.passwordEncoder(NoOpPasswordEncoder.getInstance())
      .checkTokenAccess("isAuthenticated()");
  }

  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    // @formatter:off
    clients.inMemory()
        .withClient("client1")
        .secret("secret1")
        .accessTokenValiditySeconds(1000 * 60)
        .authorizedGrantTypes("password")
      .and()
        .withClient("resourceServer")
        .secret("12345");
    // @formatter:on
  }

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    endpoints.authenticationManager(authenticationManager)
      .userDetailsService(userDetailsService);
  }
}
