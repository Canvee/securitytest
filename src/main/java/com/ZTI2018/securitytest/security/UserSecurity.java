package com.ZTI2018.securitytest.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component("userSecurity")
public class UserSecurity {
	public boolean check(Authentication authentication, HttpServletRequest request) {
		System.out.println(request.getHeader("Authorization"));
		System.out.println(authentication.getName());
		return true;
	}
}
