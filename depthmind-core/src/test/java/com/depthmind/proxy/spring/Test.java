package com.depthmind.proxy.spring;

import com.depthmind.proxy.jdk.TargetInterface;

public class Test {
	@org.junit.jupiter.api.Test
	public void test() {
		TargetInterface proxy = (TargetInterface) TestProxyFactory.getProxy(TargetInterface.class);
		proxy.query("", "");
	}
}
