package com.jyotirmayadas.security11.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

@Configuration
@EnableAuthorizationServer
public class AuthServerConfig
        extends AuthorizationServerConfigurerAdapter {

    private final AuthenticationManager authenticationManager;

    public AuthServerConfig(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    /*
    Grant types
    -----------
    authorization_code  / pkce
    password ---> deprecated
    client_credentials
    refresh_token
    implicit ---> deprecated
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        // @formatter:off
        clients
                .inMemory()
                    .withClient("client1")
                    .secret("secret1")
                    .scopes("read")
                    .authorizedGrantTypes("password")
                .and()
                    .withClient("client2")
                    .secret("secret2")
                    .scopes("read")
                    .authorizedGrantTypes("authorization_code")
                    .redirectUris("http://localhost:9090");
        // @formatter:on

    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager);
    }
}
