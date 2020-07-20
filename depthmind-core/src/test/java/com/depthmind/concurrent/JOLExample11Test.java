package com.depthmind.concurrent;

import org.junit.jupiter.api.Test;
import org.openjdk.jol.info.ClassLayout;

import static java.lang.System.out;

public class JOLExample11Test {
	static A a;

	@Test
	public void test() throws InterruptedException {
		Thread.sleep(5000);
		a = new A();
		out.println("before lock");
		out.println(ClassLayout.parseInstance(a).toPrintable());

		Thread t1= new Thread(() -> {
			synchronized (a){
				try {
					synchronized (a) {
						out.println("before wait");
						out.println(ClassLayout.parseInstance(a).toPrintable());
						a.wait();
						out.println(" after wait");
						out.println(ClassLayout.parseInstance(a).toPrintable());
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		t1.start();
		Thread.sleep(7000);
		synchronized (a) {
			a.notifyAll();
		}
	}
}
