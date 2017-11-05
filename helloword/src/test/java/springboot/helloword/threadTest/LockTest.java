package springboot.helloword.threadTest;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {
	private int incr=0;
	Lock lock = new ReentrantLock();
	public void increament(){
		lock.lock();
		try{
			incr++;
		}finally{
			lock.unlock();
		}
	}
	public static void main(String[] args) {
		final LockTest test = new LockTest();
		for (int i = 0; i <10; i++) {
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
