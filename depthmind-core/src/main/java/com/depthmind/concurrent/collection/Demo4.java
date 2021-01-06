package com.depthmind.concurrent.collection;

import java.util.concurrent.*;

/**
 * @author liuhan
 */
public class Demo4 {
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		ExecutorService executorService = Executors.newFixedThreadPool(5);
		Future<?> submit = executorService.submit(() -> {
			TimeUnit.SECONDS.sleep(1);
			return 1;
		});
		System.out.println(submit.get());
		System.out.println(submit.isDone());
	}
}
