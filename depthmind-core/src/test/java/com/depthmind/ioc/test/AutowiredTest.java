package com.depthmind.ioc.test;

import com.depthmind.ioc.autowired.AppConfig;
import com.depthmind.ioc.autowired.OrderService;
import com.depthmind.ioc.autowired.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.function.Consumer;

public class AutowiredTest {

	@Test
	public void test() {
		AnnotationConfigApplicationContext applicationContext
				= new AnnotationConfigApplicationContext(AppConfig.class);

		System.out.println(applicationContext.getBean(OrderService.class).getUserService());


	}
}
