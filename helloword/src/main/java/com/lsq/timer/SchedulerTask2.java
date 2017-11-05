package com.lsq.timer;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchedulerTask2 {
	private static final SimpleDateFormat dataForm=new SimpleDateFormat("HH:mm:ss");
	
	@Scheduled(fixedRate=1226000)
	private void reportCurrentTime() {
		System.out.println("Time Now:"+dataForm.format(new Date()));
	}
}
