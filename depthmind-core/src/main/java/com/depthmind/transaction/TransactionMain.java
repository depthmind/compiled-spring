package com.depthmind.transaction;

import com.depthmind.transaction.service.OrderService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author liuhan
 */
public class TransactionMain {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(TransactionConfig.class);
		OrderService orderService = (OrderService) applicationContext.getBean("orderService");
		orderService.pay();
	}
}
