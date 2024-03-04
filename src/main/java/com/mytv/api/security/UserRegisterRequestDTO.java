package com.mytv.api.security;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserRegisterRequestDTO {
	
	@Size(min = 4)
	@NotBlank(message = "ce champ ne peut pas etre vide et doit etre unique")
	private String username;
	
	@Size(min = 4)
	@NotBlank(message = "ce champ ne peut pas etre vide, un mot de passe est obligatoire")
	private String password;

	@Email(message = "Ce champ doit etre une adresse mail valide")
	@NotBlank(message = "ce champ ne peut pas etre vide et doit etre unique")
	private String email;
	
	//private String mobile;
	
	private List<String> roleList = new ArrayList<>();
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}


	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	public List<String> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<String> roleList) {
		this.roleList = roleList;
	}
	
	
}
