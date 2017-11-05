package springboot.helloword;

import java.io.Serializable;

public class Person implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 42L;
	
	private String name;
	
	private int age;
	
	private transient String IcCard;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getIcCard() {
		return IcCard;
	}

	public void setIcCard(String icCard) {
		IcCard = icCard;
	}
	
	public Person(String name,int age,String icCard) {
		this.name=name;
		this.age=age;
		this.IcCard=icCard;
	}

	@Override
	public String toString() {
		String rtStr = "name="+name+" age="+age+" icCard="+IcCard;
		System.out.println(rtStr);
		return rtStr;
	}
}
