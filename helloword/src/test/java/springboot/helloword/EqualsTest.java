package springboot.helloword;

public class EqualsTest {
	
	public static boolean testname() throws Exception {
		String name="123";
		String temp=name;
		try {
			if(name==temp){
				System.out.println("==");
				return true;
			}else if(name.equals(temp)){
				System.out.println("equals");
				return true;
			}
			
		} catch (Exception e) {
			System.out.println("exc");
			return true;
		}finally{
			System.out.println("finally");
			return false;
		}
	}
	
	public static void main(String[] args) throws Exception {
		boolean b=testname();
		
	}

}
