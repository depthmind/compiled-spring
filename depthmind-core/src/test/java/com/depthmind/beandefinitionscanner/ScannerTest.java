package com.depthmind.beandefinitionscanner;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ScannerTest {

	@Test
	public void test() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
		//ac.scan("com.depthmind");

	}
}
