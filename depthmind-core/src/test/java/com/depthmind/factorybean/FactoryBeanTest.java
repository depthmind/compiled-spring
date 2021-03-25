package com.depthmind.factorybean;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class FactoryBeanTest {
	@Test
	public void test() {
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
//		UserDao bean = (UserDao) annotationConfigApplicationContext.getBean("userDao");
//		System.out.println("userDao---" + bean);

		UserService userService = annotationConfigApplicationContext.getBean(UserService.class);
//		userService.list();
	}
}
