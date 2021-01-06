package com.depthmind.constructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

/**
 * @author liuhan
 */
@Service
public class OrderService implements I{
//	@Autowired(required = false)
	public OrderService() {
	}

//	@Autowired(required = true)
	public OrderService(BeanUtils beanUtils){}

//	@Autowired(required = true)
	public OrderService(UserService userService){}

	public OrderService(UserService userService, I i, String s){}

	private OrderService(UserService userService, I i){}
}
