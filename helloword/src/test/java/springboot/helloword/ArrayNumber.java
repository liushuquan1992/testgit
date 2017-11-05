package springboot.helloword;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class ArrayNumber {
	
	@Test
	public void countNumber() {
		int arrays[]={11,12,11,13,15,44,22,44};
		HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
		for(int i=0;i<arrays.length;i++){
			if(!hm.containsKey(arrays[i])){
				hm.put(arrays[i],1);
			}else{
				hm.put(arrays[i], hm.get(arrays[i])+1);
			}
		}
		for(Map.Entry<Integer, Integer> entry:hm.entrySet()){
			System.out.println(entry.getKey()+"出现了："+entry.getValue());
		}
		
	}

}
