package com.depthmind.concurrent.collection;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

public class Demo2 {
	public static void main(String[] args) {
		BlockingQueue queue = new LinkedBlockingQueue();
		new Thread(() -> {
			for (int i = 0; i < 100; i++) {
				try {
					queue.put(i);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();

		for (int i = 0; i < 5; i++) {
			new Thread(() -> {
				try {
					for (;;) {
						System.out.println(queue.take());
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}).start();
		}
	}
}
