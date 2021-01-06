package com.depthmind.sync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainTest {
	private static int i = 0;

	public static void main(String[] args) {
		/*ExecutorService executorService = Executors.newCachedThreadPool();
		executorService.submit(() -> {
			try {
				sync();
				System.out.println("t1--" + i);
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		executorService.submit(() -> {
			try {
				sync();
				System.out.println("t2--" + i);
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		executorService.shutdown();
		System.out.println("main--" + i);*/
		Thread t1 = new Thread(() -> {
			sync();
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		Thread t2 = new Thread(() -> sync());

		t1.start();
		t2.start();
		System.out.println("main--" + i);
	}

	public static void sync() {
		synchronized (MainTest.class) {
			i ++;
		}
	}
}
