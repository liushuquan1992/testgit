package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Test2 {
	 public static void main(String[] args) {
	       Integer i=0;
	        
	        Stack s=new Stack<>();
	        List list=new ArrayList<>();
	        list.add("4");
	        list.add("5");
	        s.push("1");
	        s.push("0");
	        s.push("2");
	        s.push("3");
	        s.push("4");
	        s.push("4");
	        s.push("4");
	        System.out.println(s.elements());
	        System.out.println(s.containsAll(list));
	        System.out.println(s.empty());
	        System.out.println(s.pop());
	        System.out.println(s.empty());
	 }
}
