package com.depthmind.jvm;

public class DeadLockTest {
	public static void main(String[] args) {
		Object o1 = new Object();
		Object o2 = new Object();

		new Thread(() -> {
			synchronized (o1) {
				System.out.println("o1-");
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				synchronized (o2) {
					System.out.println("o2-");
				}
			}
		}).start();

		new Thread(() -> {
			synchronized (o2) {
				System.out.println("o1");
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				synchronized (o1) {
					System.out.println("o2");
				}
			}
		}).start();
	}
}
