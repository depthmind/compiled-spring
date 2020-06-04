package com.depthmind.proxy;

import com.depthmind.proxy.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import sun.misc.ProxyGenerator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Proxy;

public class Test {
	@org.junit.jupiter.api.Test
	public void test() {
		AnnotationConfigApplicationContext configApplicationContext =
				new AnnotationConfigApplicationContext(AppConfig.class);
		UserDao indexDao = (UserDao) configApplicationContext.getBean("indexDao");
		indexDao.query();
		/*byte[] generateProxyClass = ProxyGenerator.generateProxyClass("UserDao", new Class[]{UserDao.class});
		try {
			FileOutputStream fileOutputStream = new FileOutputStream("/Users/liuhan/Desktop/UserDaoImpl.class");
			fileOutputStream.write(generateProxyClass);
			fileOutputStream.flush();
			fileOutputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
	}
}
