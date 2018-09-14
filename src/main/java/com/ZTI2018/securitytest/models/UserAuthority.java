package com.ZTI2018.securitytest.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "authorities")
public class UserAuthority {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
//	@Column(name="auth_username")
//	@JsonIgnore
//    private String username;
    
	@Column(name = "authority")
	private String authority;
	
	@ManyToOne//(fetch = FetchType.EAGER)//(targetEntity=AppUser.class)
//    @JoinColumn(name="username", nullable=false)
//    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
	private AppUser appuser; 
	
//	public String getUsername() {
//		return username;
//	}

//	public void setUsername(String username) {
//		this.username = username;
//	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public AppUser getAppuser() {
		return appuser;
	}

	public void setAppuser(AppUser appuser) {
		this.appuser = appuser;
	}
	
	
}
