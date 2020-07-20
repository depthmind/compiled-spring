package com.depthmind.log;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.Test;

public class JCLTest {
	@Test
	public void test() {
		Log logger = LogFactory.getLog(JCLTest.class);
		logger.error("JCL");
	}
}
