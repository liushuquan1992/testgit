package com.lsq.autoconf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.lsq.base.service.UserService;
import com.lsq.util.StringUtil;

@Component
@RabbitListener(queues = RabbitConfig.QUEUE_NAME_USER, containerFactory = "userSaveFactory")
public class UserFromMQSaveToDB {
	
	private Logger logger = LoggerFactory.getLogger(UserFromMQSaveToDB.class);
	
	@Autowired
	private UserService userService;
	
	@RabbitHandler
	private void process(String msg) {
		try {
	           if(StringUtil.isNotBlank(msg)){
	        	   JSONObject object = JSONObject.parseObject(msg);
	        	   userService.handlerInsertUser(object);
	           }
	        } catch (Exception e) {
	        	logger.error(e.getMessage()+ " 原始 msg: "+ msg);
	        }

	}
}
