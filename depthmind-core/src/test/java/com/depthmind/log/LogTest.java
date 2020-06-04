package com.depthmind.log;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.Test;

public class LogTest {
	@Test
	public void test() {
		Log log = LogFactory.getLog(LogTest.class);
		System.out.println("这里打印日志");
		log.error("------LogTest.......");
	}
}
