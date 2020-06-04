package com.depthmind.log;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author liuhan
 */
public class LogTest {
	public static void main(String[] args) {
		Log log = LogFactory.getLog(LogTest.class);
		log.info("JCL");
	}
}
