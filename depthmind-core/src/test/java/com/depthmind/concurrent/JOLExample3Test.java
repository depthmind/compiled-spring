package com.depthmind.concurrent;

import org.junit.jupiter.api.Test;
import org.openjdk.jol.info.ClassLayout;

import static java.lang.System.out;

public class JOLExample3Test {
	static A a;
	@Test
	public void test() throws InterruptedException {
		Thread.sleep(5000); // sleep之后将导致都是偏向锁
		a= new A();
		a.hashCode(); // hash之后不能偏向，无锁->轻量锁->无锁
		out.println("befor lock");
		out.println(ClassLayout.parseInstance(a).toPrintable());//无锁：偏向锁？
		synchronized (a){
			out.println("lock ing");
			out.println(ClassLayout.parseInstance(a).toPrintable());
		}

		out.println("after lock");
		out.println(ClassLayout.parseInstance(a).toPrintable());
	}

}
