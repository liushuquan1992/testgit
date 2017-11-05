package springboot.helloword.threadTest;

import org.junit.Test;

public class ThreadTest implements Runnable{

	int i=0;
	@Test
	public void run() {
		while(true){
			System.out.println(i++);
		}
		
	}

}
