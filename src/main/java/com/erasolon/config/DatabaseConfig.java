package com.erasolon.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * @author erasolon
 *
 * created on 18/08/2018
 */

@Configuration
public class DatabaseConfig {
	
	@Value("${database.url}")
	private String database_url;
	
	@Value("${database.password}")
	private String database_password;
	
	@Value("${database.username}")
	private String database_username;
	
	@Bean(name="dataSource")
	public DriverManagerDataSource datasource() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		driverManagerDataSource.setUrl(database_url);
		driverManagerDataSource.setUsername(database_username);
		driverManagerDataSource.setPassword(null);
		
		return driverManagerDataSource;
	}
	

}
