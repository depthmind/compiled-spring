package com.depthmind.proxy.jdk;

/**
 * 目标对象的具体实现类
 * @author l
 */
public class TargetImpl implements TargetInterface {
	@Override
	public void query(String str1, String str2) {
		System.out.println("target");
	}
}
