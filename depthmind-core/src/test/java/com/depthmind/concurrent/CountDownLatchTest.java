package com.depthmind.concurrent;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class CountDownLatchTest {
	// volatile int num = 0; 此时加了volatile也不能保证打印结果正确
	AtomicInteger num = new AtomicInteger(0);

	@org.junit.jupiter.api.Test
	public void test() throws InterruptedException {
		ReentrantLock reentrantLock = new ReentrantLock(false);
		Semaphore semaphore = new Semaphore(2);
		CountDownLatch countDownLatch = new CountDownLatch(5);
		ExecutorService executorService = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++) {
			int finalI = i;
			executorService.execute(() -> {
				try {
					semaphore.acquire();
					printNum();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				semaphore.release();
				countDownLatch.countDown();
			});
		}

		//Thread.sleep(2000);
		countDownLatch.await();
		executorService.shutdown();
		System.out.println("------" + num);
	}

	private void printNum() throws InterruptedException {
		for (int i = 0; i < 2000; i++) {
			System.out.println(num.incrementAndGet());
		}
		TimeUnit.SECONDS.sleep(2);
	}
}
