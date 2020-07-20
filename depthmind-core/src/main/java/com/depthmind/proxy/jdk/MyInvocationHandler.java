package com.depthmind.proxy.jdk;

import com.depthmind.proxy.jdk.reflect.CustomInvocationHandler;

import java.lang.reflect.Method;

/**
 * @author liuhan
 */
public class MyInvocationHandler implements CustomInvocationHandler {
	TargetInterface target;
	public MyInvocationHandler(TargetInterface target) {
		this.target = target;
	}

	@Override
	public void invoke(Object proxy, Method method, Object[] args) throws Exception {
		method.invoke(target, args);
	}
}
