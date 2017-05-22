package com.truedo.login;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@SpringBootApplication
public class LoginApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoginApplication.class, args);
	}
	
	@Bean(name = "dataSource")
	   public DriverManagerDataSource dataSource() {
	        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
	        driverManagerDataSource.setUrl(ConfigConstants.DatabaseName);
	        driverManagerDataSource.setUsername(ConfigConstants.setUsername);
	        driverManagerDataSource.setPassword(ConfigConstants.setPassword);
	        return driverManagerDataSource;
	    }
	
	 
}
