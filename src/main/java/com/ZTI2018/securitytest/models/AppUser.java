package com.ZTI2018.securitytest.models;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "users")
public class AppUser {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
	
	private String name;
	
	
    @Column(unique = true)
    private String username;
    
    @JsonIgnore
    private String password;
    private boolean enabled = true;
    private Date lastLogin;
    
//    @OneToMany(mappedBy="username")
    //@JoinColumn(name="username", nullable=false)
    
//    @JoinTable(
//            name = "users_authorities",
//            joinColumns = @JoinColumn(name = "username"),
//            inverseJoinColumns = @JoinColumn(name = "auth_username")
//    )
    @OneToMany(mappedBy="appuser",cascade = CascadeType.ALL)//(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnoreProperties("appuser")
    private List<UserAuthority> authorities = new ArrayList<>();
    
    @OneToMany(mappedBy = "appuser")
	@JsonIgnoreProperties("appuser")
	private List<ItemList> lists = new ArrayList<>();
    
    public AppUser() {
    	
    }
    
    public AppUser(String name, String email, String password) {
        this.username = email;
        this.name = name;
        this.password = password;
    }
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public Date getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public List<UserAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<UserAuthority> authorities) {
		this.authorities = authorities;
	}
    
    
}
