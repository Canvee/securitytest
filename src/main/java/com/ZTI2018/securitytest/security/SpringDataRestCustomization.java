package com.ZTI2018.securitytest.security;

import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.stereotype.Component;


@Component
public class SpringDataRestCustomization extends RepositoryRestConfigurerAdapter {
	/**
	 * Configuring Cors
	 * https://docs.spring.io/spring-data/rest/docs/current/reference/html/#customizing-sdr.configuring-cors
	 * Java Documentation
	 * https://docs.spring.io/spring-data/rest/docs/current/api/org/springframework/data/rest/core/config/RepositoryRestConfiguration.html
	 * https://docs.spring.io/spring/docs/5.0.x/javadoc-api/org/springframework/web/servlet/config/annotation/CorsRegistration.html
	 */
  @Override
  public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {

    config.getCorsRegistry().addMapping("/api/**")
      .allowedOrigins("*")
      .allowedMethods("*")
//      .allowedHeaders("header1", "header2", "header3")
//      .exposedHeaders("header1", "header2")
      .allowCredentials(false).maxAge(3600);
  }
}