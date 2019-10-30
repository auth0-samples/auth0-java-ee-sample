package com.auth0.example.web;

import com.auth0.AuthenticationController;
import com.auth0.example.security.Auth0AuthenticationConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Responsible for redirecting to Auth0 to initiate the authentication flow when a login request is made.
 */
@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(LoginServlet.class);

    private final Auth0AuthenticationConfig config;
    private final AuthenticationController authenticationController;

    @Inject
    LoginServlet(Auth0AuthenticationConfig config, AuthenticationController authenticationController) {
        this.config = config;
        this.authenticationController = authenticationController;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.info("in LoginServlet");
        response.sendRedirect(buildAuthUrl(request));
    }

    private String buildAuthUrl(HttpServletRequest request) {
        String redirectUrl = String.format(
                "%s://%s:%s/callback",
                request.getScheme(),
                request.getServerName(),
                request.getServerPort()
        );

        return authenticationController.buildAuthorizeUrl(request, redirectUrl)
                .withScope(config.getScope())
                .build();
    }
}
