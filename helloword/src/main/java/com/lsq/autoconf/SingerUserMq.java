package com.lsq.autoconf;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class SingerUserMq {
	
	@Autowired
	@Qualifier("singleRabbitTemplate")
	private AmqpTemplate singleRabbitTemplate;
	
	public void sendMessage(String content) {
		singleRabbitTemplate.convertAndSend(RabbitConfig.QUEUE_NAME_SINGLE,content);
	}
}
