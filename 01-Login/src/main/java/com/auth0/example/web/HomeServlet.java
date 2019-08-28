package com.auth0.example.web;

import com.auth0.example.security.Auth0JwtPrincipal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

/**
 * Handles requests to the home page. If there is an authenticated principal, sets the profile data on the request
 * for rendering by the view.
 */
@WebServlet(urlPatterns = "")
public class HomeServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Principal principal = request.getUserPrincipal();

        // If we have an Auth0JwtPrincipal on the request, store the user profile on the request.
        if (principal instanceof Auth0JwtPrincipal) {
            Auth0JwtPrincipal auth0JwtPrincipal = (Auth0JwtPrincipal) principal;
            request.setAttribute("profile", auth0JwtPrincipal.getIdToken().getClaims());
        }

        /*
         * Note we always forward to the home page, even when there is no authenticated principal.
         * This is so the home page will always be shown, and the user will be able to login if not
         * already authenticated.
         */
        request.getRequestDispatcher("/WEB-INF/jsp/home.jsp").forward(request, response);
    }
}
