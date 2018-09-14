package com.ZTI2018.securitytest.security;

import org.springframework.security.core.Authentication;

public class ListSecurity {
	public boolean checkUserId(Authentication authentication, int id) {
		return true;
	}
}
