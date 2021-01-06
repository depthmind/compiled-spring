package com.depthmind.concurrent.demo13;

import java.util.LinkedList;
import java.util.List;

public class Container1Test {
	volatile List list = new LinkedList();
	int max = 10;
	public void add(int i) {
		list.add(i);
	}

	public int size() {
		return list.size();
	}

	public static void main(String[] args) {
		Container1Test test = new Container1Test();
		new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				test.add(i);
				System.out.println("add" + i);
			}
		}).start();
		new Thread(() -> {
			while(true) {
				if (test.size() == 5) {
					break;
				}
			}
		}).start();
	}
}
