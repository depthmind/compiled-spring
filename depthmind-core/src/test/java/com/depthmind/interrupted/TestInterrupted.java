package com.depthmind.interrupted;

import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

public class TestInterrupted {
	@Test
	public void test() throws InterruptedException {
		OneThread oneThread = new OneThread();
		oneThread.start();
	}
}

class OneThread extends Thread {
	@Override
	public void run() {
		while (!Thread.interrupted()) {
			try {
				TimeUnit.SECONDS.sleep(1);
				System.out.println("running");
			} catch (InterruptedException e) {
				Thread.interrupted();
			}

		}
	}
}