package springboot.helloword.threadTest;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest {
 
	private AtomicInteger incr = new AtomicInteger();
	
	public void increament(){
		incr.incrementAndGet();
	}
	public static void main(String[] args) {
		final AtomicIntegerTest test = new AtomicIntegerTest();
		for (int i = 0; i < 10000; i++) {
			new Thread(){
				public void run() {
					for (int j = 0; j < 10000; j++) {
						test.increament();
					}
				};
			}.start();
		}
		while(Thread.activeCount()>1){
			Thread.yield();
		}
		System.out.println(test.incr);
	}
	
}
