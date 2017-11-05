package com.lsq.redis;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

public class PipelineTest {
	
	@Test
	public static void main(String[] args){
		Jedis jr=new Jedis("127.0.0.1", 6379);
		int count=1000;
		long start=System.currentTimeMillis();
//		withoutPipeline(count);
		for (int i = 0; i < count; i++) {
			jr.incr("testKey"+i);
		}
		long end =System.currentTimeMillis();
		System.out.println("withOutPipeline:"+(end-start));
		
		start=System.currentTimeMillis();
		//usePipeline(count);
		Pipeline p=jr.pipelined();
		for (int i = 0; i < count; i++) {
			p.incr("testKey:"+i);
		}
		p.sync();
		end =System.currentTimeMillis();
		System.out.println("usePipeline:"+(end-start));
		
		
	}
	
}
