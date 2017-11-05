package com.lsq.timer;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchedulerTask {
	private int count=0;
	
	@Scheduled(cron="* * 3 * * ?")
	private void process() {
		System.out.println("this is scheduler task runing "+(count++));
	}
}
