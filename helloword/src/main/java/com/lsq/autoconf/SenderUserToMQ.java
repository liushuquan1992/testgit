package com.lsq.autoconf;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class SenderUserToMQ {
	
	@Autowired
    @Qualifier("userSaveRabbitTemplate")
    private AmqpTemplate userSaveRabbitTemplate;
	
	public void sendMessage(String content) {
		userSaveRabbitTemplate.convertAndSend(RabbitConfig.QUEUE_NAME_USER, content);
    }
}
