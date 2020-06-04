package com.depthmind.configuration.test;

import com.depthmind.configuration.config.MyConfiguration;
import com.depthmind.configuration.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.util.Scanner;

public class ConfigurationTest {
	@Test
	public void test() throws InterruptedException, IOException {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyConfiguration.class);
		UserService userService = applicationContext.getBean(UserService.class);
		System.in.read();
	}
}
