package com.depthmind.configuration.config;

import com.depthmind.configuration.service.CustomerService;
import com.depthmind.configuration.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author liuhan
 */
@Configuration
@Import(ImportedConfiguration.class)
public class MyConfiguration {
	@Bean
	public UserService userService() {
		return new UserService();
	}

	@Bean
	public CustomerService customerService() {
		this.userService();
		return new CustomerService();
	}
}
