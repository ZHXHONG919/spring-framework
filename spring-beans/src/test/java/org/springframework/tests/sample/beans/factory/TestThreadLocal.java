package org.springframework.tests.sample.beans.factory;

/**
 * @author xiaohong.zheng
 * @since 2018/12/29 11:10
 */

public class TestThreadLocal {
	ThreadLocal<Long> longLocal = new ThreadLocal<Long>(){
		protected Long initialValue() {
			return Thread.currentThread().getId();
		};
	};
	ThreadLocal<String> stringLocal = new ThreadLocal<String>(){;
		protected String initialValue() {
			return Thread.currentThread().getName();
		};
	};


	public void set() {
		longLocal.set(Thread.currentThread().getId());
		stringLocal.set(Thread.currentThread().getName());
	}

	public long getLong() {
		return longLocal.get();
	}

	public String getString() {
		return stringLocal.get();
	}

	public static void main(String[] args) throws InterruptedException {
		final TestThreadLocal test = new TestThreadLocal();

		test.set();
		System.out.println(test.getLong());
		System.out.println(test.getString());


		Thread thread1 = new Thread(){
			public void run() {
				test.set();
				System.out.println(test.getLong());
				System.out.println(test.getString());
			};
		};
		thread1.start();
		thread1.join();

		System.out.println(test.getLong());
		System.out.println(test.getString());
	}
}