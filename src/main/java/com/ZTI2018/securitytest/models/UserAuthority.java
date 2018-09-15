package com.ZTI2018.securitytest.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "authorities")
public class UserAuthority {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_generator")
	@SequenceGenerator(name="user_generator", sequenceName = "user_seq", initialValue=4, allocationSize=1)
	private Long id;

	@Column(name = "authority")
	private String authority;
	
	//(fetch = FetchType.EAGER)//(targetEntity=AppUser.class)
//    @JoinColumn(name="username", nullable=false)
//    @OnDelete(action = OnDeleteAction.CASCADE)
	@ManyToOne
    @JsonIgnore
	private AppUser appuser; 
	
	public UserAuthority()
	{
		this("ROLE_USER");
	}
	
	public UserAuthority(String authority)
	{
		this.authority = authority;
	}
	
	
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
