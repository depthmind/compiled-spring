package com.depthmind.factorybean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author liuhan
 */
public class MyInvocationHandler implements InvocationHandler {
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("printsomethind");
		return null;
	}
}
