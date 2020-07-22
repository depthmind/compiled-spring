package com.depthmind.factorybean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liuhan
 */
@Service
public class UserService {
	@Autowired
	UserDao userDao;

	public void list() {
//		System.out.println("service");
		userDao.printSomething();
	}
}
