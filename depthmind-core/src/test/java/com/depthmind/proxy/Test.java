package com.depthmind.proxy;

import com.depthmind.proxy.config.AppConfig;
import com.depthmind.proxy.jdk.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import sun.misc.ProxyGenerator;
import sun.misc.URLClassPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.net.URL;
import java.net.URLClassLoader;

public class Test {
	@org.junit.jupiter.api.Test
	public void test() {
		TargetInterface target = new TargetImpl();
		//jdk的动态代理
//		byte[] bytes = ProxyGenerator.generateProxyClass("aaa", new Class[]{target.getClass()});
//		try {
//			FileOutputStream fileOutputStream = new FileOutputStream("/Users/liuhan/Desktop/aaa.class");
//			fileOutputStream.write(bytes);
//			fileOutputStream.flush();
//			fileOutputStream.close();
//		} catch (Exception e){
//			e.printStackTrace();
//		}
//
//		TargetInterface proxy = (TargetInterface) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new JdkInvocationHandler(target));
//		proxy.query("", "");
//		AnnotationConfigApplicationContext configApplicationContext =
//				new AnnotationConfigApplicationContext(AppConfig.class);
//		UserDao indexDao = (UserDao) configApplicationContext.getBean("indexDao");
//		indexDao.query();
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
		ProxyUtil.getInstance(TargetImpl.class, new MyInvocationHandler(target));
		// 手写动态代理
		/*TargetInterface  target = new TargetImpl1();
		target.query("1", "1");
		try {
			ClassLoader systemClassLoader = new URLClassLoader(new URL[]{new URL("file:\\\\/Users/liuhan/Project/spring-framework/depthmind-core/build/classes/java/main/")});
			Class<?> aClass = systemClassLoader.loadClass("com.depthmind.proxy.jdk.Proxy");
			Constructor<?>[] constructors = aClass.getConstructors();
			TargetInterface targetInterface = null;
			for (Constructor<?> constructor : constructors) {
				//if (constructor.getParameterCount() == 0) {
					targetInterface = (TargetInterface) constructor.newInstance(target);
				//}
			}
			targetInterface.query("1","2");
		} catch (Exception e) {
			e.printStackTrace();
		}*/
	}
}
