package springboot.helloword.threadTest;

public class DoubleCheckTest {
	//单列模式
	private volatile static DoubleCheckTest instance = null;
	
	private DoubleCheckTest() {
	}
	
	public static DoubleCheckTest getInstance(){
		if(instance==null){
			synchronized (DoubleCheckTest.class) {
				if(instance==null){
					instance = new DoubleCheckTest();
				}
			}
		}
		return instance;
	}
	
}
