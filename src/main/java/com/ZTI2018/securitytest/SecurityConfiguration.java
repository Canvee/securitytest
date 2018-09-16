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
	
//	@Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**");
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
	    
//	    @Bean
//	    CorsConfigurationSource corsConfigurationSource() {
//			CorsConfiguration configuration = new CorsConfiguration();
//			configuration.setAllowedOrigins(Arrays.asList("https://localhost:8080"));
//			configuration.setAllowedMethods(Arrays.asList("GET","POST"));
//			UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//			source.registerCorsConfiguration("/**", configuration);
//			return source;
//		}

}
