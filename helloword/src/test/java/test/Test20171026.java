package test;

import org.junit.Test;

public class Test20171026 {
	enum color{
		GREEN,BLUE,RED,YELLOW;
	}
	
	@Test
	public void test(){
		color red=color.RED;
		switch (red) {
		case RED:
			red=color.YELLOW;
			break;

		default:
			break;
		}
	}
	
	@Test
	public void testString(){
		String a=new String("hello");
		String b="hello";
		String c="hel"+"lo";
		System.out.println(a==b);
		System.out.println(a==c);
		System.out.println(a.equals(b));
		System.out.println(a.equals(c));
		System.out.println(a.intern()==b.intern());
		char[] cs=a.toCharArray();
		System.out.println(cs[0]);
	}

}
