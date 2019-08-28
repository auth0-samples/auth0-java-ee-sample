package com.auth0.example.web;

import com.auth0.example.security.Auth0JwtPrincipal;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.util.Base64;


/**
 * Handles requests for the profile page. If no authenticated principal for the request is found, redirects the user
 * to login.
 */
@WebServlet(urlPatterns = {"/profile"})
public class ProfileServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(ProfileServlet.class);

    private static final String JSON_PROCESSING_ERROR_MESSAGE = "Error converting JWT claims to JSON";

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Principal principal = request.getUserPrincipal();
        if (principal instanceof Auth0JwtPrincipal) {
            LOG.info("found Auth0JwtPrincipal, forwarding to view");
            Auth0JwtPrincipal auth0JwtPrincipal = (Auth0JwtPrincipal) principal;
            request.setAttribute("profile", auth0JwtPrincipal.getIdToken().getClaims());

            String profileJson;
            try {
                profileJson = claimsAsJson(auth0JwtPrincipal.getIdToken());
            } catch (IOException ioe) {
                profileJson = JSON_PROCESSING_ERROR_MESSAGE;
                LOG.error(JSON_PROCESSING_ERROR_MESSAGE, ioe);
            }

            request.setAttribute("profileJson", profileJson);
            request.getRequestDispatcher("/WEB-INF/jsp/profile.jsp").forward(request, response);
        } else {
            LOG.info("No principal found for this request, redirecting to login");
            request.getSession().setAttribute("Referer", request.getRequestURI());
            request.getRequestDispatcher("/login").forward(request, response);
        }
    }

    /**
     * Utility method to convert the JWT claims into JSON for use by the display of this sample.
     * @param decodedJWT
     * @return a JSON-formatted string used by the view for displaying profile information.
     * @throws IOException if unable to convert JWT into JSON.
     */
    private static String claimsAsJson(DecodedJWT decodedJWT) throws IOException {

        /*
         * Decode the JWT payload and get value as pretty-printed JSON for the Profile UI.
         *
         * NOTE: Typical applications will not need to do this. This is only done here
         * for display purposes of this sample application.
         */
        byte[] decodedBytes = Base64.getUrlDecoder().decode(decodedJWT.getPayload());
        String decoded = new String(decodedBytes, StandardCharsets.UTF_8);

        ObjectMapper objectMapper = new ObjectMapper();

        Object json = objectMapper.readValue(decoded, Object.class);
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
    }
}
