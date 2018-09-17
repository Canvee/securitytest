package com.ZTI2018.securitytest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
/**
 * Main class in webapplication
 * Contains main method
 * 
 * @author canvee
 *
 */
@ServletComponentScan
@SpringBootApplication
public class SecuritytestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecuritytestApplication.class, args);
	}
}
