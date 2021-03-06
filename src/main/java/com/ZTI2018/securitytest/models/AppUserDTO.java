package com.ZTI2018.securitytest.models;

/**
 * AppUser Data Transer Object
 * Creates transfer object for transmission between client and server
 * 
 * @author canvee
 *
 */
public class AppUserDTO {
	private String mail;
	private String username;
	private String password;
	
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
