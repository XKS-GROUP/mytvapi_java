package com.mytv.api.firebase.token;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import com.google.firebase.auth.FirebaseToken;

import java.util.Collection;

public class FirebaseAuthenticationToken extends AbstractAuthenticationToken {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Object principal;
    private final Object credentials;

    public FirebaseAuthenticationToken(FirebaseToken firebaseToken) {
        super(null) ;
        this.principal = firebaseToken;
        this.credentials = "N/A";
        setAuthenticated(true);
    }

    public FirebaseAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return credentials;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }
}
