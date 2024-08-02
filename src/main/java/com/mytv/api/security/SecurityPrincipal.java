/**
 *
 */
package com.mytv.api.security;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.mytv.api.model.gestUser.User;
import com.mytv.api.service.gestUser.WUserService;


@Service
public class SecurityPrincipal {
	private static SecurityPrincipal securityPrincipal = null;

	private Authentication principal = SecurityContextHolder.getContext().getAuthentication();

	private static WUserService userService;

	@Autowired
	private SecurityPrincipal(WUserService userService) {
		SecurityPrincipal.userService = userService;
	}


	public static SecurityPrincipal getInstance() {
		securityPrincipal = new SecurityPrincipal(userService);
		return securityPrincipal;
	}


	public User getLoggedInPrincipal() {
		if (principal != null) {
			UserDetails loggedInPrincipal = (UserDetails) principal.getPrincipal();
			
			return userService.findByUserEmail(loggedInPrincipal.getUsername());
		}
		return null;
	}


	public Collection<?> getLoggedInPrincipalAuthorities() {
		return ((UserDetails) principal.getPrincipal()).getAuthorities();
	}

}
