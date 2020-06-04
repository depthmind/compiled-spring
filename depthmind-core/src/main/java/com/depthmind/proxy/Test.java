package com.depthmind.proxy;

import com.depthmind.proxy.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author liuhan
 */
public class Test {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext configApplicationContext =
				new AnnotationConfigApplicationContext(AppConfig.class);
		UserDao useDao = (UserDao) configApplicationContext.getBean("useDao");
		useDao.query();
	}
}
