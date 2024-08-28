package com.mytv.api.firebase.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
            	
            	 // Gestion de l'exception et envoi de la réponse HTTP
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json");

                Map<String, Object> responseData = new HashMap<>();
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

                responseData.put("TimeStamp", new Date());
                responseData.put("Message", "TOKEN_INVALID");
                responseData.put("Status", HttpServletResponse.SC_UNAUTHORIZED);
                responseData.put("Data", Map.of("message","Votre session a expiré ou le token est invalide."));
                response.getWriter().write(new ObjectMapper().writeValueAsString(responseData));
                
                //return;
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
