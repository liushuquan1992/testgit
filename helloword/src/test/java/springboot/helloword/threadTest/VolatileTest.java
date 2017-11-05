package springboot.helloword.threadTest;

public class VolatileTest {
	private volatile int incr=0;
	
	public void increment(){
		incr++;
	}
	public static void main(String[] args) {
		final VolatileTest test = new VolatileTest();
		for (int i = 0; i < 10; i++) {
			new Thread(){
				public void run(){
					for (int j = 0; j < 8000; j++) {
						test.increment();
					}
				}
			}.start();
		}
		while(Thread.activeCount()>1){
			Thread.yield();
		}
		System.out.println(test.incr);
	}
}
