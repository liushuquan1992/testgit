package test;

class A{
	public static int x;
	static{x=B.y+1;}
}
public class B {
	public static int y=A.x+1;
	static{}
	public static void main(String[] args) {
		System.out.println("A.x="+A.x+"  B.y="+B.y);
	}
}

