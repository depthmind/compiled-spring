package com.depthmind.ioc.xml;

/**
 * @author liuhan
 */
public class UserService {
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void test() {
		System.out.println("ok......");
	}
}
