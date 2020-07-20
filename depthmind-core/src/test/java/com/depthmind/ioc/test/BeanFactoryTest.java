package com.depthmind.ioc.test;

import com.depthmind.ioc.xml.BeanFactory;
import com.depthmind.ioc.xml.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Role;

public class BeanFactoryTest {
	@Test
	public void test() {
		BeanFactory beanFactory = new BeanFactory("spring.xml");
		UserService userService = (UserService) beanFactory.getBean("userService");
		userService.test();
	}
	Role
}
