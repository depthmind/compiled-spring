package com.depthmind.configuration.config;

import com.depthmind.configuration.service.CustomerService;
import com.depthmind.configuration.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author liuhan
 */
@Configuration
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
