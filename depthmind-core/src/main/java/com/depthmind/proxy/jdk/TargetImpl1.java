package com.depthmind.proxy.jdk;

/**
 * @author liuhan
 */
public class TargetImpl1 implements TargetInterface {
	@Override
	public void query(String str1, String str2) {
		System.out.println("target1");
	}
}
