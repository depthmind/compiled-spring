package com.depthmind.proxy.aspect;

import com.depthmind.proxy.UserDao;
import com.depthmind.proxy.UserDaoImpl;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.DeclareParents;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author liuhan
 */
@Component
@Aspect
public class MyAspect {
	/**
	 * ---引入----实现
	 */
//	@DeclareParents(value = "com.depthmind.proxy.UserDao", defaultImpl = UserDaoImpl.class)
//	UserDao dao;

//	@Pointcut("execution(public * com.depthmind.proxy.*(..))")
	@Pointcut("within(com.depthmind.proxy..*)")
	public void executionPointcut(){}

	@Before(value = "executionPointcut()")
	public void log() {
		System.out.println("log");
	}
}
