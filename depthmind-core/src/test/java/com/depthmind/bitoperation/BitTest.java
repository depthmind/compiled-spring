package com.depthmind.bitoperation;

import org.junit.jupiter.api.Test;
import sun.misc.Unsafe;

import java.util.concurrent.ConcurrentHashMap;

public class BitTest {
	@Test
	public void test() {
		//0000 0000 0000 1111 >> 1 = 111
		// 			   1 1110
//		System.out.println(Integer.toBinaryString(15));
//		System.out.println(15>>6);

		System.out.println(Integer.MAX_VALUE);
		System.out.println(Integer.MIN_VALUE);
		System.out.println(Byte.MAX_VALUE);
//		System.out.println(15<<1); //30
//		1111 1
//		System.out.println(15|1); //15
//		System.out.println(15&1); //1
//		System.out.println(15^1); //1  记错了 应该是14  异或--不相同才为1


		// Unsafe不可以这样用
//		Unsafe U = Unsafe.getUnsafe();
//		U.compareAndSwapInt()
	}
}
