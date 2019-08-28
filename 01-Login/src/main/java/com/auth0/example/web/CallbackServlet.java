package com.auth0.example.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Handles requests to the callback URI. Redirects to the home page or to the referring page if set.
 */
@WebServlet(urlPatterns = {"/callback"})
public class CallbackServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(CallbackServlet.class);

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        /*
         * Since we perform the token exchange in Auth0AuthenticationMechanism (which happens prior to this Servlet's
         * execution in the filter chain), we simply redirect the home page or to the referring URI if one was set
         * in the session.
         */
        String referer = (String) request.getSession().getAttribute("Referer");
        String redirectTo = referer != null ? referer : "/";

        LOG.info("After callback, redirecting to: {}", referer);
        response.sendRedirect(redirectTo);
    }
}
