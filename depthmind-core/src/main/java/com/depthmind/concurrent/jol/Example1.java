package com.depthmind.concurrent.jol;

import org.openjdk.jol.info.ClassLayout;
import static java.lang.System.out;

public class Example1 {
	public static void main(String[] args) {
		Example1 a = new Example1();
		out.print(ClassLayout.parseInstance(a).toPrintable());
		out.println("hashcode-----" + Integer.toHexString(a.hashCode()));
		out.print(ClassLayout.parseInstance(a).toPrintable());
		synchronized (a) {
			out.println("locking----");
			out.print(ClassLayout.parseInstance(a).toPrintable());
		}
		out.println("before hash----");
		out.print(ClassLayout.parseInstance(a).toPrintable());
		out.println("hashcode-----" + Integer.toHexString(a.hashCode()));
		out.println("after hash----");
		out.print(ClassLayout.parseInstance(a).toPrintable());
	}
}
