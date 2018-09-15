package com.ZTI2018.securitytest.models;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "users")
public class AppUser {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "auth_generator")
	@SequenceGenerator(name="auth_generator", sequenceName = "auth_seq", initialValue=3, allocationSize=1)
    private long id;
	
	private String mail;
	
	
    @Column(unique = true)
    private String username;
    
    @JsonIgnore
    private String password;
    private boolean enabled = true;
    private Timestamp lastLogin;
    
//    @OneToMany(mappedBy="username")
    //@JoinColumn(name="username", nullable=false)
    
//    @JoinTable(
//            name = "users_authorities",
//            joinColumns = @JoinColumn(name = "username"),
//            inverseJoinColumns = @JoinColumn(name = "auth_username")
//    )
    @OneToMany(mappedBy="appuser",cascade = CascadeType.ALL)//(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    //@JsonIgnoreProperties("appuser")
    private List<UserAuthority> authorities = new ArrayList<>();
    
    @OneToMany(mappedBy = "appuser")
	@JsonIgnoreProperties("appuser")
	private List<ItemList> lists = new ArrayList<>();
    
    public AppUser() {
    	
    }
    
    public AppUser(String username, String mail, String password) {
        this.username = username;
        this.mail = mail;
        this.password = password;
    }
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
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
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public Timestamp getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(Timestamp lastLogin) {
		this.lastLogin = lastLogin;
	}

	public List<UserAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<UserAuthority> authorities) {
		this.authorities = authorities;
	}
    
    
}
