package com.depthmind.concurrent.collection;

import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;

public class Demo3 {
	public static void main(String[] args) throws InterruptedException {
		LinkedTransferQueue linkedTransferQueue = new LinkedTransferQueue();
		new Thread(() -> {
			try {
				System.out.println("t1" + linkedTransferQueue.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();

		new Thread(() -> {
			try {
				System.out.println("t2" + linkedTransferQueue.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();

//		try {
//			TimeUnit.SECONDS.sleep(5);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		linkedTransferQueue.transfer("aaa");
		linkedTransferQueue.transfer("bbb");
		System.out.println(linkedTransferQueue.size());
//		ExecutorService
//		Executors
//		Collections.
	}
}
