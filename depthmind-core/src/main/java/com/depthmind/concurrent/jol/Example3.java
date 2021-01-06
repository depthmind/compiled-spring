package com.depthmind.concurrent.jol;

import java.util.ArrayList;
import java.util.List;
import org.openjdk.jol.info.ClassLayout;
import static java.lang.System.out;

/**
 * @author liuhan
 */
public class Example3 {
	public static void main(String[] args) throws InterruptedException {
		List<A1> list = new ArrayList<>(100);
		for (int i = 0; i < 100; i++) {
			A1 a = new A1();
			list.add(a);
		}
		Thread t1 = new Thread(() -> {

			for (int i = 0; i < 100; i++) {
				A1 a = list.get(i);
				synchronized (a) {
					if (i == 10) {
						out.println("t1--------预期偏向t1");
						out.print(ClassLayout.parseInstance(a).toPrintable());
					}
				}
			}
		});
		t1.start();
		t1.join();
		out.println("main 预期是偏向锁"+10 + ClassLayout.parseInstance(list.get(10)).toPrintable());
		Thread t2 = new Thread(() -> {
			for (int i = 0; i < 100; i++) {
				A1 a = list.get(i);
//				if (i == 20) {
//					a = list.get(10);
//				}
				synchronized (a) {
					if (i == 18) {
						//此时为轻量锁
						out.println("预期轻量锁");
						out.print(ClassLayout.parseInstance(a).toPrintable());
					}
					//发生批量重偏向，偏向t2
					if (i == 19) {
						out.println("预期偏向t2");
						out.print(ClassLayout.parseInstance(a).toPrintable());
					}
					if (i == 20) {
						out.println("预期偏向t2");
						out.print(ClassLayout.parseInstance(a).toPrintable());
					}
					if (i == 41) {
						out.println("预期偏向t2");
						out.print(ClassLayout.parseInstance(a).toPrintable());
					}
				}
			}
		});
		t2.start();
	}
}

class A1{}
