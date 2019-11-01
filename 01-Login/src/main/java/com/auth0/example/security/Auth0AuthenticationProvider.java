package com.auth0.example.security;

import com.auth0.AuthenticationController;
import com.auth0.jwk.JwkProvider;
import com.auth0.jwk.JwkProviderBuilder;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

/**
 * Bean that defines the {@link AuthenticationController} used to login with Auth0.
 */
@ApplicationScoped
public class Auth0AuthenticationProvider {

    @Produces
    public AuthenticationController authenticationController(Auth0AuthenticationConfig config) {
        // JwkProvider required for RS256 tokens. If using HS256, do not use.
        JwkProvider jwkProvider = new JwkProviderBuilder(config.getDomain()).build();
        return AuthenticationController.newBuilder(config.getDomain(), config.getClientId(), config.getClientSecret())
                .withJwkProvider(jwkProvider)
                .build();
    }
}
