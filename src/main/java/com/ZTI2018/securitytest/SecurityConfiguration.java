package com.ZTI2018.securitytest;


import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension;
import org.springframework.web.context.WebApplicationContext;

import com.ZTI2018.securitytest.filter.TestFilter;
import com.ZTI2018.securitytest.security.CustomUserDetailsService;
import com.ZTI2018.securitytest.security.WebSecurity;

/**
 * Main security configuration class
 * <p>
 * Manages
 * <ul>
 * 	<li> AuthenticationManagerBuilder
 * 	<li> HttpSecurity
 * </ul>
 * 
 * @author canvee
 */

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
    private WebApplicationContext applicationContext;
	private CustomUserDetailsService userDetailsService;
	@Autowired
	private DataSource dataSource;
	
	
	@PostConstruct
    public void completeSetup() {
        userDetailsService = applicationContext.getBean(CustomUserDetailsService.class);
    }
	
	/**
	 * Function configures the AuthenticationManagerBuilder
	 * Authentication happens threw the custom userDetailsService
	 * Password is encoded
	 * Users are stored in the dataSource database
	 */
    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
            .passwordEncoder(encoder())
            .and()
            .authenticationProvider(authenticationProvider())
            .jdbcAuthentication()
            .dataSource(dataSource)
            .usersByUsernameQuery("select username,password,enabled from users where username = ?")
    		.authoritiesByUsernameQuery("select username,authority from authorities where username = ?");
    }
	
    /**
     * The function configures and controls the Authorisation of the users
     * Using basic http authentication
     */
	@Override
	public void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
			.cors().and()
			.authorizeRequests()
				// enable registration for all users
				// TODO make it available only for anonymous users
				.antMatchers("/user/register").permitAll()
				.antMatchers("/api").permitAll()
				.antMatchers(HttpMethod.GET, "**/api/users").hasRole("ADMIN")
				.antMatchers("/api/users/{userId}/**").access("@webSecurity.checkUserId(authentication,#userId)")
				.antMatchers("/api/lists/{listId}/**").access("@webSecurity.checkListId(authentication,#listId)")
				.antMatchers("/api/items/{itemId}/**").access("@webSecurity.checkitemId(authentication,#itemId)")
				// all other requests
				.anyRequest().fullyAuthenticated()
				//.anyRequest().hasRole("ADMIN")
				.and()
			// if filter needed
			//.addFilterAfter(testFilter(), BasicAuthenticationFilter.class)
			.httpBasic();
		httpSecurity.csrf().disable();
	}

	/**
	 * Data acces object provider sets the userDetailsService and the passwordEncoder
	 * Used internal by Spring Security
	 * @return DaoAuthenticationProvider
	 */
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
	    final DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	    authProvider.setUserDetailsService(userDetailsService);
	    authProvider.setPasswordEncoder(encoder());
	    return authProvider;
	}
	
	/**
	 * PasswordEncoder bean stores centrally the paswordEncoder object
	 * @return password encoder instance
	 */
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder(11);
	}
	
	/**
	 * SecurityEvaluationContextExtensions creates instance of class
	 * For internal use for Spring Security
	 * @return returns instance of SecurityEvaluetionContextExtension
	 */
	@Bean
	public SecurityEvaluationContextExtension securityEvaluationContextExtension() {
		return new SecurityEvaluationContextExtension();
	}
	
	/**
	 * webSecurity function returns object holding functions controlling access for specific URIs in configure(HttpSecurity httpSecurity)
	 * 
	 * @see configure(HttpSecurity httpSecurity)
	 * @return webSecurity instance
	 */
	@Bean
	public WebSecurity webSecurity() {
		return new WebSecurity();
	}
	
	/**
	 * testFilter creates filter
	 * example for spring security filter usage on special URI
	 * For internal Spring Security usage
	 * To use filter @ServletComponentScan annotation needed on main Spring Boot class with main function
	 * 
	 * @see SecuritytestApplication
	 * @return TestFilter instance
	 */
	@Bean 
	TestFilter testFilter() {
		return new TestFilter();
	}
}
