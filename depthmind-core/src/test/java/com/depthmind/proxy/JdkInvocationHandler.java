package com.depthmind.proxy;

import com.depthmind.proxy.jdk.TargetInterface;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class JdkInvocationHandler implements InvocationHandler {
	TargetInterface target;

	/**
	 * InvocationHandler的实现类需要提供一个构造器，用来接收目标对象
	 * 否则接口中的method是无法执行的，因为没有对象就没有办法执行对象的方法
	 * @param target 目标对象
	 */
	public JdkInvocationHandler(TargetInterface target) {
		this.target = target;
	}

	/**
	 *
	 * @param proxy 代理对象
	 * @param method 目标对象方法
	 * @param args 目标对象参数
	 * @return
	 * @throws Throwable
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("advisor");
		return method.invoke(target, args);
	}
}
