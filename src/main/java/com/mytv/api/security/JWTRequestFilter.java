package com.mytv.api.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.mytv.api.model.gestUser.User;
import com.mytv.api.repository.JwtRepository;
import com.mytv.api.service.gestUser.WUserService;

import io.jsonwebtoken.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTRequestFilter extends OncePerRequestFilter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JWTTokenUtil jwtTokenUtil;

	@Autowired
	private JwtRepository jwtRep;

	@Autowired
	private WUserService userService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException, SignatureException {

		final String requestTokenHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		
		if (requestTokenHeader == null || requestTokenHeader == "" || requestTokenHeader.isBlank() || requestTokenHeader.isEmpty() || !requestTokenHeader.startsWith("Bearer ")) {
			
			
			chain.doFilter(request, response);
			
			System.out.println("Je suis appeler   ");
			
			return;
		}
		
		else {
			
		
			String jwtToken = requestTokenHeader.split(" ")[1].trim();
			
			String username = jwtTokenUtil.getUsernameFromToken(jwtToken);
			UserDetails userValueObject = userDetailsService.loadUserByUsername(username);
	
			System.out.println(" Je suis appeler avant chaque requete ");
			
			User usr = userService.findByUserEmail(username);
			
		    if (jwtTokenUtil.validateToken(jwtToken, userValueObject) && !jwtRep.findByValue(jwtToken).isEmpty() && usr.isValide()) {
				System.out.println("Le nom d'utilisateur est  "+username);

				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userValueObject, null, userValueObject.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
			

		    chain.doFilter(request, response);
		}
	}
}
