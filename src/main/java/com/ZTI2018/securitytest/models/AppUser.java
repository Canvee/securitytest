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

/**
 * AppUser Model 
 * Used by hibernate to create tables in database
 * 
 * @author canvee
 *
 */
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
    
    /**
     * public default constructor
     */
    public AppUser() {
    	
    }
    
    /**
     * public constructor initalisitn object with values
     * @param username - type String - username must be unique to all usernames
     * @param mail - type String - mail of user
     * @param password - password of user
     */
    public AppUser(String username, String mail, String password) {
        this.username = username;
        this.mail = mail;
        this.password = password;
    }
    
    /**
     * Getting a id value.
     * @return value of 'id' column from chosen entity.
     * @type String
     */
	public long getId() {
		return id;
	}
	
	/**
     * Setting a id to an user.
     * New password value is encoded and assigned to a object attribute.
     * @param password - type String, value assigned to a 'password' column in chosen entity.
     * */
	public void setId(long id) {
		this.id = id;
	}
	
	/**
     * Getting a mail value.
     * @return value of 'mail' column from chosen entity.
     * @type String
     */
	public String getMail() {
		return mail;
	}
	
	/**
	 * Setting mail to a user
	 * @param mail - type String, value assigned to a 'mail' column in chosen entity
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	/**
     * Getting a username value.
     * @return value of 'username' column from chosen entity.
     * @type String
     */
	public String getUsername() {
		return username;
	}
	
	/**
	 * Setting username to a user
	 * @param username - type String, value assigned to a 'username' column in chosen entity
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
     * Getting a password value.
     * @return value of 'password' column from chosen entity.
     * @type String
     */
	public String getPassword() {
		return password;
	}
	
	/**
     * Setting a password to an user for further authorization.
     * New password value is encoded and assigned to a object attribute.
     * @param password - type String, value assigned to a 'password' column in chosen entity.
     * */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
     * Getting a enabled value.
     * @return value of 'enabled' column from chosen entity.
     * @type boolean
     */
	public boolean isEnabled() {
		return enabled;
	}
	
	/**
	 * Setting enabled to a user
	 * @param username - type boolean, value assigned to a 'enabled' column in chosen entity
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	/**
     * Getting a lastLogin value.
     * @return value of 'lastLogin' column from chosen entity.
     * @type Timestamp
     */
	public Timestamp getLastLogin() {
		return lastLogin;
	}
	
	/**
	 * Setting lastLogin to a user
	 * @param lastLogin - type boolean, value assigned to a 'lastLogin' column in chosen entity
	 */
	public void setLastLogin(Timestamp lastLogin) {
		this.lastLogin = lastLogin;
	}

	/**
     * Getting list of authorities values.
     * @return value of 'authorities' column from chosen entity.
     * @type List<UserAuthority>
     */
	public List<UserAuthority> getAuthorities() {
		return authorities;
	}
	
	/**
	 * Setting authorities to a user
	 * @param authorities - type List<UserAuthority>, value assigned to a 'authorities' column in chosen entity
	 */
	public void setAuthorities(List<UserAuthority> authorities) {
		this.authorities = authorities;
	}
    
    
}
