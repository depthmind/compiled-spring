package com.depthmind.log;

import org.junit.jupiter.api.Test;
import org.openjdk.jol.info.ClassData;
import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.layouters.Layouter;

import java.util.Deque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

public class JULTest {
	@Test
	public void test() {
		Logger logger = Logger.getLogger(this.getClass().getName());
		logger.info("JUL");
	}
}
