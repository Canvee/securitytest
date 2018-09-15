package com.ZTI2018.securitytest.security;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.ZTI2018.securitytest.models.AppUser;
import com.ZTI2018.securitytest.repositories.ItemRepository;
import com.ZTI2018.securitytest.repositories.ListRepository;
import com.ZTI2018.securitytest.repositories.UserRepository;

@Component("userSecurity")
public class WebSecurity {
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	ListRepository listRepository;
	@Autowired
	ItemRepository itemRepository;
	
	public boolean anonymous(Authentication authentication, HttpServletRequest request) { 
		System.out.println("inside");
		if (authentication == null)
			return true;
		else 
			return false;
	}
	
	public boolean check(Authentication authentication, HttpServletRequest request) { 
		System.out.println("request " + request.getHeader("Authorization"));
		Principal principal = request.getUserPrincipal();
		System.out.println("URI: "+ request.getRequestURI());
		System.out.println("PRINCIPAL: " + principal.getName());
		System.out.println("AUTHENTICATION: " + authentication.getName());
		System.out.println(principal.getName().equals(authentication.getName()));
		return principal.getName().equals(authentication.getName());
	}
	
	public boolean checkUserId(Authentication authentication, int id) {
		System.out.println(authentication.getName());
		AppUser user = userRepository.findByUsername(authentication.getName());
		if (user == null) 
		{
			System.out.println("User not found");
			return false;
		}
		return user.getId() == id;
	}
	
	public boolean checkListId(Authentication authentication, int id) {
		// get owner 
		// compare list owner with authentication
		return true;
	}
}
