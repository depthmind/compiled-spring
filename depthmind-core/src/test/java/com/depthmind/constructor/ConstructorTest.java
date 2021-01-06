package com.depthmind.constructor;

import org.jetbrains.annotations.TestOnly;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConstructorTest {
	@Test
	public void test() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
		ac.getBean("orderService");
	}
}
