package com.depthmind.concurrent.jol;

import java.util.ArrayList;
import java.util.List;
import org.openjdk.jol.info.ClassLayout;
import static java.lang.System.out;

/**
 * 验证  BiasedLockingBulkRebiasThreshold 是针对对象集合的
 *  -XX:BiasedLockingBulkRevokeThreshold=39 针对的是某一类的实例对象 而不是 所有类的对象
 */
public class BiasedLockJOLTest {


	public static void main(String[] args) throws Exception {
		//睡眠超过偏向锁延迟，以使用偏向锁
		Thread.sleep(6000);

		List<A> list = new ArrayList<>();

		//初始化数据
		for (int i = 0; i < 100; i++) {
			list.add(new A());

		}

		Thread t1 = new Thread() {
			String name = "1";

			@Override
			public void run() {
				out.printf(name);
				for (A a : list) {
					synchronized (a) {
						if (a == list.get(10)) {
							out.println("t1 预期是偏向锁"+10 + ClassLayout.parseInstance(a).toPrintable());
						}
					}
				}
				try {
					Thread.sleep(100000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		t1.start();
		Thread.sleep(5000);
		out.println("main 预期是偏向锁"+10 + ClassLayout.parseInstance(list.get(10)).toPrintable());

		Thread t2 = new Thread() {
			String name = "2";

			@Override
			public void run() {
				out.printf(name);

				for(int i = 0;i<100;i++){
					A a = list.get(i);
					// hack 为了在批量重偏向发生后再次加锁前面使用了轻量级锁的对象
					if(i==20){
						a= list.get(9);
					}

					synchronized (a) {
						if ( i==10) {
							//已经经过偏向锁撤销，并使用轻量级锁的对象，释放后  状态依为001 无锁状态
							out.println("t2 i=10 get(1)预期是无锁" +  ClassLayout.parseInstance(list.get(1)).toPrintable());
							//因为和t1交替使用对象a 没有发生竞争，但偏向锁已偏向，另外不满足重偏向条件，所以使用轻量级锁
							out.println("t2 i=10 get(i) 预期轻量级锁 " + i + ClassLayout.parseInstance(a).toPrintable());
						}
						if ( i== 19) {
							//已经经过偏向锁撤销，并使用轻量级锁的对象，在批量重偏向发生后。不会影响现有的状态  状态依然为001
							out.println("t2  i=19  get(10)预期是无锁" + 10 + ClassLayout.parseInstance(list.get(10)).toPrintable());
							//满足重偏向条件后，已偏向的对象可以重新使用偏向锁 将线程id指向当前线程，101
							out.println("t2  i=19  get(i) 满足重偏向条件20 预期偏向锁 " + i + ClassLayout.parseInstance(a).toPrintable());
							//满足重偏向条件后，已偏向还为需要加锁的对象依然偏向线程1 因为偏向锁的撤销是发生在下次加锁的时候。这里没有执行到同步此对象，所以依然偏向t1
							out.println("t2  i=19  get(i) 满足重偏向条件20 但后面的对象没有被加锁，所以依旧偏向t1 " + i + ClassLayout.parseInstance(list.get(40)).toPrintable());
						}
						if (i == 20) {
							//满足重偏向条件后，再次加锁之前使用了轻量级锁的对象，依然轻量级锁，证明重偏向这个状态只针对偏向锁。已经发生锁升级的，不会退回到偏向锁
							out.println("t2  i=20 满足偏向条件之后，之前被设置为无锁状态的对象，不可偏向，这里使用的是轻量级锁  get(9)预期是轻量级锁 "  + ClassLayout.parseInstance(a).toPrintable());
						}
					}
				}
				try {
					Thread.sleep(100000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		t2.start();
		Thread.sleep(5000);

	}
}