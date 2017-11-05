package springboot.helloword.threadTest;

public class SynorTest {
	private int incr=0;
	
	public synchronized void increment(){
		incr++;
	}
	
	public static void main(String[] args) {
		final SynorTest test = new SynorTest();
		for (int i = 0; i <10; i++) {
			new Thread(){
				public void run() {
					for(int j=0;j<10000;j++){
							test.increment();
						};
				}
			}.start();
		}
		while(Thread.activeCount()>1){
			Thread.yield();
		}
		System.out.println(test.incr);
	}
	
}
