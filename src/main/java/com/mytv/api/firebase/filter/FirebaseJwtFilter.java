package com.mytv.api.firebase.filter;

import com.google.firebase.auth.FirebaseToken;
import com.mytv.api.firebase.token.FirebaseTokenUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class FirebaseJwtFilter extends OncePerRequestFilter {

    @Autowired
    private FirebaseTokenUtil firebaseTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        final String requestTokenHeader = request.getHeader("Authorization");

        String idToken = null;
        FirebaseToken decodedToken = null;

        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            idToken = requestTokenHeader.substring(7);
            try {
                decodedToken = firebaseTokenUtil.verifyToken(idToken);
            } catch (Exception e) {
                logger.error("Token invalide", e);
            }
        }

        if (decodedToken != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    decodedToken.getUid(), null, null);
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request, response);
    }
}
