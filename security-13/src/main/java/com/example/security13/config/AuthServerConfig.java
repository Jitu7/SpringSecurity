package com.example.security13.config;

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
public class AuthServerConfig
  extends AuthorizationServerConfigurerAdapter {

  private final UserDetailsService userDetailsService;
  private final AuthenticationManager authenticationManager;

  @Override
  public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
    security
      .passwordEncoder(NoOpPasswordEncoder.getInstance())
      .checkTokenAccess("isAuthenticated()"); // permitAll()
  }

  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    clients.inMemory()
      .withClient("client1")
      .secret("secret1")
      .scopes("read")
      .accessTokenValiditySeconds(5000) // seconds
      .authorizedGrantTypes("password", "refresh_token");
  }

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    endpoints.authenticationManager(authenticationManager);
    endpoints.userDetailsService(userDetailsService);
  }
}
