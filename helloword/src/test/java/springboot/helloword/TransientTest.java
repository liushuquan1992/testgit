package springboot.helloword;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class TransientTest {
	
	public static void main(String[] args) {
		Person p= new Person("lily", 26, "888888888888888888888");
		p.toString();
		//读入磁盘
		try {
			ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("D:/log.out"));
			o.writeObject(p);
			o.close();
		} catch (Exception e) {
			e.getMessage();
		}
		//从磁盘读取数据
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("D:/log.out"));
			Person p1=(Person) in.readObject();
			p1.toString();
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		
	}

}
