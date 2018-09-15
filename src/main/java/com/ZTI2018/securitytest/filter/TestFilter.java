package com.ZTI2018.securitytest.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.filter.GenericFilterBean;

@WebFilter(urlPatterns = "/api/users")
public class TestFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException 
	{
		HttpServletRequest httpReq = (HttpServletRequest) request;
		if (httpReq.getRequestURI().equals("/api/users") && httpReq.getMethod() == "POST")
		{
			System.out.println("Filter is on");
		} 

		chain.doFilter(request, response);
	}

}
