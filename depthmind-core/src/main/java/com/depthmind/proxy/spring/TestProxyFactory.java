package com.depthmind.proxy.spring;

import com.depthmind.proxy.jdk.TargetInterface;
import org.springframework.aop.TargetSource;
import org.springframework.aop.framework.ProxyFactory;

/**
 * @author liuhan
 */
public class TestProxyFactory {
	public static Object getProxy(Class<?> target) {
		ProxyFactory proxyFactory = new ProxyFactory(target);
		proxyFactory.setTargetSource(new TargetSource() {
			@Override
			public Class<?> getTargetClass() {
				return null;
			}

			@Override
			public boolean isStatic() {
				return false;
			}

			@Override
			public Object getTarget() throws Exception {
				return null;
			}

			@Override
			public void releaseTarget(Object target) throws Exception {

			}
		});
		proxyFactory.addInterface(target.getInterfaces()[0]);
		return proxyFactory.getProxy(target.getClassLoader());
	}
}
