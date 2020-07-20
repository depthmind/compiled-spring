package com.depthmind.synchronize;

/**
 * @author liuhan
 */
public class SyncTest {
	public static void main(String[] args) {

	}

	public void test() {
		synchronized (this) {
			System.out.println("11");
		}
	}

	public synchronized void test1() {
		System.out.println("11");
	}
}
