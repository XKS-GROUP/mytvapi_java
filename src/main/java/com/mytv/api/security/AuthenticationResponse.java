package com.mytv.api.security;

import java.io.Serializable;

import com.mytv.api.model.gestUser.User;


public class AuthenticationResponse implements Serializable{
	private static final long serialVersionUID = -8091879091924046844L;

	private User usr;
	private String token;
	private String refresh;

	public AuthenticationResponse(String token) {
		this.token = token;
		this.refresh = null;
	}
	public AuthenticationResponse(String token, String refresh) {

		this.token = token;
		this.refresh = refresh;
	}

	public AuthenticationResponse(String token, String refresh, User usr) {

		this.usr = usr;
		this.token = token;
		this.refresh = refresh;
	}

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * @return the refresh
	 */
	public String getRefresh() {
		return refresh;
	}

	/**
	 * @param refresh the refresh to set
	 */
	public void setRefresh(String refresh) {
		this.refresh = refresh;
	}

	public User getUsr() {
		return usr;
	}

	public void setUsr(User usr) {
		this.usr = usr;
	}



}
