package org.programming.pet.offerua.security.service.factory;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationTokenFactory {

    public UsernamePasswordAuthenticationToken create(UserDetails userDetails, HttpServletRequest request) {
        var authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        var authDetails = createWebAuthenticationDetailsSource(request);
        authToken.setDetails(authDetails);
        return authToken;
    }

    public UsernamePasswordAuthenticationToken create(String username, String password) {
        return new UsernamePasswordAuthenticationToken(username, password);
    }

    private WebAuthenticationDetails createWebAuthenticationDetailsSource(HttpServletRequest request) {
        return new WebAuthenticationDetailsSource()
                .buildDetails(request);
    }
}