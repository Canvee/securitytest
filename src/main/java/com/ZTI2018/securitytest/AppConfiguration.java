package com.ZTI2018.securitytest;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * AppConfiguration sets all configuration needed by the server side app
 * returns
 * <ul>
 * 	<li> DataSource
 * 	<li> LocalContainerEntityManagerFactoryBean
 * </ul>
 * @author canvee
 *
 */
@PropertySource("classpath:application.properties")
@EnableJpaRepositories(basePackages = { "com.ZTI2018.securitytest.repositories" })
@Import(SecurityConfiguration.class)
public class AppConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
    private Environment env;
	
	
	
	
	/**
	 * Bean sets DataSource object to connect with the external database
	 * Values are read by the @see org.springframework.core.env.Environment field
	 * Parameters are set in the "src/main/resources/application.propperties" file
	 * 
	 * @return 	dataSource object
	 */
    @Bean
    public DataSource dataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("driverClassName"));
        dataSource.setUrl(env.getProperty("url"));
        dataSource.setUsername(env.getProperty("user"));
        dataSource.setPassword(env.getProperty("password"));
        return dataSource;
    }
    
    
    
    /**
     * Bean sets LocalContainerEntityManagerFactoryBean to configure hibernate helping to configure the classes
     * 
     * @return LocalContainerEntityManagerFactoryBean with configuration
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(new String[] { "com.ZTI2018.securitytest.models" });
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        //em.setJpaProperties(additionalProperties());
        return em;
    }
}
