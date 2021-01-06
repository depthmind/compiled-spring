package com.depthmind.concurrent.jol;

import java.util.ArrayList;
import java.util.List;
import org.openjdk.jol.info.ClassLayout;
import static java.lang.System.out;

/**
 * @author liuhan
 */
public class Example2 {
	public static void main(String[] args) throws InterruptedException {
		List<A> list = new ArrayList<>(100);
		Thread t1 = new Thread(() -> {
			for (int i = 0; i < 100; i++) {
				A a = new A();
				synchronized (a) {
					if (i==18) {
						out.println("t1");
						out.print(ClassLayout.parseInstance(a).toPrintable());
					}
					list.add(a);
				}
			}
		});
		t1.start();
		t1.join();
		Thread t2 = new Thread(() -> {
			A a;
			for (int i = 0; i < 30; i++) {
				a = list.get(i);
				synchronized (a) {
					if (i==18||i==22) {
						out.println("t2---" + i);
						out.print(ClassLayout.parseInstance(a).toPrintable());
					}
				}
			}
		});
		t2.start();
		Thread t3 = new Thread(() -> {
			A a;
			for (int i = 30; i < 100; i++) {
				a = list.get(i);
				synchronized (a) {
					if (i==38||i==52) {
						out.println("t3---" + i);
						out.print(ClassLayout.parseInstance(a).toPrintable());
					}
				}
			}
		});
		t3.start();
	}
}

class A {

}
