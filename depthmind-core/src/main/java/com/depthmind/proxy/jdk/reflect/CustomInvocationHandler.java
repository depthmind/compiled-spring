package com.depthmind.proxy.jdk.reflect;

import java.lang.reflect.Method;

/**
 * @author liuhan
 */
public interface CustomInvocationHandler {
	/**
	 * 用来执行代理逻辑
	 * @param proxy 代理对象
	 * @param method 目标对象方法
	 * @param args 目标方法参数
	 * @throws Exception e
	 */
	void invoke(Object proxy, Method method, Object[] args) throws Exception;
}
