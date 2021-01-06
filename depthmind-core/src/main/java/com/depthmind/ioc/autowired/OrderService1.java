package com.depthmind.ioc.autowired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public class OrderService1 {
	UserService userService;

//	public void setUserService(UserService userService) {
//		this.userService = userService;
//	}

	public UserService getUserService() {
		return userService;
	}
}
