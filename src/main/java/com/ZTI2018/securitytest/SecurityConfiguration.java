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
	
//	@Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.jdbcAuthentication().dataSource(dataSource)
//		.usersByUsernameQuery("select username,password,enabled from users where username = ?")
//		.authoritiesByUsernameQuery("select username,authority from authorities where username = ?");
////        auth.inMemoryAuthentication()
////                .withUser("ajay").password("{noop}test").roles("USER").and()
////                .withUser("demo").password("{noop}test2").roles("ADMIN");
//    }
	
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
//        System.out.println(encoder().encode("admin"));
    }
	
	@Override
	public void configure(HttpSecurity httpSecurity) throws Exception {
		//httpSecurity.authorizeRequests().anyRequest().fullyAuthenticated().and().httpBasic();
		httpSecurity
			.authorizeRequests()
				.antMatchers(HttpMethod.GET, "**/api/users").hasRole("ADMIN")
				.antMatchers("/user/register**").permitAll()
				//.antMatchers(HttpMethod.POST, "**/api/users").permitAll().a
				.antMatchers("/api/users/{userId}/**").access("@webSecurity.checkUserId(authentication,#userId)")
				//.antMatchers("/api/users/**").access("@webSecurity.check(authentication,request)")
				.antMatchers("/api/lists/{listId}/**").access("@webSecurity.checkListId(authentication,#listId)")
				.anyRequest().fullyAuthenticated().and()
			//.addFilterAfter(testFilter(), BasicAuthenticationFilter.class)
				//.anyRequest().fullyAuthenticated().and()
			.httpBasic();
		httpSecurity.csrf().disable();
	}
	
	  @Bean
	    public DaoAuthenticationProvider authenticationProvider() {
	        final DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	        authProvider.setUserDetailsService(userDetailsService);
	        authProvider.setPasswordEncoder(encoder());
	        return authProvider;
	    }

	    @Bean
	    public PasswordEncoder encoder() {
	        return new BCryptPasswordEncoder(11);
	    }
	    
	    @Bean
	    public SecurityEvaluationContextExtension securityEvaluationContextExtension() {
	        return new SecurityEvaluationContextExtension();
	    }
	    
	    @Bean
	    public WebSecurity webSecurity() {
	    	return new WebSecurity();
	    }
	    
	    @Bean 
	    TestFilter testFilter() {
	    	return new TestFilter();
	    }
}
