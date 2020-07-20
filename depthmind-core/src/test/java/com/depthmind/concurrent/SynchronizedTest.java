package com.depthmind.concurrent;

import org.junit.jupiter.api.Test;
import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.layouters.Layouter;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SynchronizedTest {

	static int i = 0;
	@Test
	public void test() throws InterruptedException {
		ExecutorService executors = Executors.newCachedThreadPool();
		CountDownLatch countDownLatch = new CountDownLatch(10);
		long start = System.currentTimeMillis();
		for (int i = 0; i < 10; i++) {
			executors.execute(()-> {
				for (int j = 0; j < 10000; j++) {
					test0();
				}
				otherNormalMethod();
				countDownLatch.countDown();
			});
		}
		countDownLatch.await();
		executors.shutdown();
		System.out.println(System.currentTimeMillis() - start);
	}
	AA a = new AA();

	// 普通方法
	public void test0() {
		i++;
		System.out.println(Thread.currentThread().getId() + "---" + i);
		// 重量级锁
		//System.out.println(ClassLayout.parseInstance(this).toPrintable());
	}

	public synchronized void test1() {
		i++;
		System.out.println(Thread.currentThread().getId() + "---" + i);
		// 重量级锁
		//System.out.println(ClassLayout.parseInstance(this).toPrintable());
	}

	public static synchronized void test2() {
		i++;
		System.out.println(Thread.currentThread().getId() + "---" + i);
		// 重量级锁
		//System.out.println(ClassLayout.parseInstance(this).toPrintable());
	}

	public void test3() {
		synchronized (this) {
			i++;
			System.out.println(Thread.currentThread().getId() + "---" + i);
		}
	}

	public void otherNormalMethod() {
		System.out.println(Thread.currentThread().getId() + "ok");
	}
}

class AA {

}
