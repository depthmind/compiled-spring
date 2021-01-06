package com.depthmind.concurrent.collection;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

public class Demo1 {
	private static Map map = new HashMap();
	private static Map map1 = new ConcurrentHashMap();
	private static Map map2 = new Hashtable();
	public static void main(String[] args) throws InterruptedException {
		Thread[] threads = new Thread[100];
		Random random = new Random(10000);
		CountDownLatch latch = new CountDownLatch(threads.length);
		long l = System.currentTimeMillis();
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(() -> {
				for (int j = 0; j < 10000; j++) {
					map2.put(random.nextDouble(), random.nextDouble());
				}
			});
			latch.countDown();
		}
		latch.await();
		System.out.println(System.currentTimeMillis() - l);
	}
}
