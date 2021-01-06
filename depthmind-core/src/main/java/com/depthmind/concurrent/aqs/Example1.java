package com.depthmind.concurrent.aqs;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author liuhan
 */
public class Example1 {
	public static void main(String[] args) {
		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3,5,2000L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
		ReentrantLock reentrantLock = new ReentrantLock(true);
		for (int i = 0; i < 3; i++) {
			threadPoolExecutor.submit(() -> {
				reentrantLock.lock();
				logic();
				reentrantLock.unlock();
			});
		}
		threadPoolExecutor.shutdown();

		/*List<Thread> list = new ArrayList<>(3);
		for (int i = 0; i < 3; i++) {
			Thread thread = new Thread(() -> {
				reentrantLock.lock();
				logic();
				reentrantLock.unlock();
			});
			thread.start();
			list.add(thread);
		}
		list.get(1).start();*/
	}

	public static void logic() {
		try {
			TimeUnit.SECONDS.sleep(30);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
