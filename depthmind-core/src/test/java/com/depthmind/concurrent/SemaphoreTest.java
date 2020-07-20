package com.depthmind.concurrent;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class SemaphoreTest {
	AtomicInteger num = new AtomicInteger(0);
	@Test
	public void test() throws InterruptedException {
		Semaphore semaphore = new Semaphore(2);
		CountDownLatch countDownLatch = new CountDownLatch(5);
		ExecutorService executorService = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++) {
			executorService.submit(() -> {
				System.out.println("availablePermits--" + semaphore.availablePermits());
				try {
					semaphore.acquire();
					printNum();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

//				printNum();
				semaphore.release();
			});
			countDownLatch.countDown();
		}

		countDownLatch.await();
		executorService.shutdown();
		System.out.println("------" + num);
	}
	private void printNum() {
		for (int i = 0; i < 2000; i++) {
			System.out.println(num.incrementAndGet());
		}
	}
}
