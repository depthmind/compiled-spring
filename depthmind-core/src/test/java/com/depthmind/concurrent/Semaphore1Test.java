package com.depthmind.concurrent;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class Semaphore1Test {

	// 排队总人数（请求总数）
	public static int clientTotal = 10;

	// 可同时受理业务的窗口数量（同时并发执行的线程数）
	public static int threadTotal = 2;

	@Test
	public void test() throws Exception {
		ExecutorService executorService = Executors.newCachedThreadPool();
		final Semaphore semaphore = new Semaphore(threadTotal);
		final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
		for (int i = 0; i < clientTotal; i++) {
			final int count = i;
			executorService.execute(() -> {
				try {
					semaphore.acquire(1);
					resolve(count);
					semaphore.release(1);
				} catch (Exception e) {
					e.printStackTrace();
				}
				countDownLatch.countDown();
			});
		}
		countDownLatch.await();
		executorService.shutdown();
	}

	private static void resolve(int i) throws InterruptedException {
		System.out.println("服务号{}，受理业务中。。。" + i);
		Thread.sleep(2000);
	}
}
