package com.att;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.h2.server.web.WebServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import com.att.dao.CustomerDaoImpl;

/**
 * 
 * @author Jishnu Mahesh
 *
 */
@SpringBootApplication
public class ServicesApplication {

	@Inject
	private Environment environment;

	@Inject
	private DataSource dataSource;

	/**
	 * Bean to create DataSource based on application.properties
	 * 
	 * @return
	 */
	/*// Not Required those are Spring Provided Properties it will create automatically
	 * @Bean public DataSource dataSource() { BasicDataSource dataSource = new
	 * BasicDataSource(); dataSource.setDriverClassName(environment.getProperty(
	 * "spring.datasource.driverClassName"));
	 * dataSource.setUrl(environment.getProperty("spring.datasource.url"));
	 * dataSource.setUsername(environment.getProperty(
	 * "spring.datasource.username"));
	 * dataSource.setPassword(environment.getProperty(
	 * "spring.datasource.password")); return dataSource; }
	 */

	/**
	 * Bean to Register H2
	 * 
	 * @return
	 */
	@Bean
	public ServletRegistrationBean h2servletRegistration() {
		ServletRegistrationBean registration = new ServletRegistrationBean(new WebServlet());
		registration.addUrlMappings("/h2-console/*");
		return registration;
	}

	/**
	 * Bean for CustomerDaoImpl
	 * 
	 * @return
	 */
	@Bean
	public CustomerDaoImpl customerDaoImpl() {
		CustomerDaoImpl customerDaoImpl = new CustomerDaoImpl();
		//customerDaoImpl.setDataSource(dataSource());
		customerDaoImpl.setDataSource(dataSource);
		return customerDaoImpl;
	}

	/**
	 * Spring-Boot Run
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(ServicesApplication.class, args);
	}

}
