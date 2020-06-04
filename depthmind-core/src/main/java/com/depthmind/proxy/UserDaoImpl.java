package com.depthmind.proxy;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Repository;

/**
 * @author liuhan
 */
@Repository("userDao")
public class UserDaoImpl implements UserDao {
	@Override
	public void query() {
		System.out.println("UserDaoImpl");
	}
}
