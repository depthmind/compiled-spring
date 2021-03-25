package com.depthmind.transaction.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author liuhan
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService{
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void pay() {
		System.out.println("pay");
	}
}
