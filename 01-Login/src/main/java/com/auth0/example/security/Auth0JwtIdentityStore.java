package com.auth0.example.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;

/**
 * Implementation of {@link IdentityStore}, responsible for validating the caller's credentials.
 */
@ApplicationScoped
public class Auth0JwtIdentityStore implements IdentityStore {

    private static final Logger LOG = LoggerFactory.getLogger(Auth0JwtIdentityStore.class);

    @Override
    public CredentialValidationResult validate(final Credential credential) {
        CredentialValidationResult result = CredentialValidationResult.NOT_VALIDATED_RESULT;
        if (credential instanceof Auth0JwtCredential) {
            Auth0JwtCredential auth0JwtCredential = (Auth0JwtCredential) credential;
            result = new CredentialValidationResult(auth0JwtCredential.getAuth0JwtPrincipal());
        }
        LOG.info("validation result: {}", result.getStatus());
        return result;
    }
}
