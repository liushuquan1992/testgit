package com.lsq.base.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.session.SessionProperties.Redis;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.lsq.autoconf.JedisOperator;
import com.lsq.autoconf.SenderUserToMQ;
import com.lsq.autoconf.SingerUserMq;
import com.lsq.base.mapper.UserMapper;
import com.lsq.base.vo.User;

@Service
public class UserService {
	
	private Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private JedisOperator jedisOperator;
	
	@Autowired
	private SenderUserToMQ senderUserToMq;
	
	@Autowired
	private SingerUserMq singerUserMq;
	
	public List<User> getUserList() {
		List<User> list = userMapper.getUserList();
		jedisOperator.set("name",list.get(0).getName());
		String name = jedisOperator.get("name");
		return list;
	}
	
	//保存用户信息
	public void insertUser(JSONObject object) {
		//将用户信息保存到mq
		boolean sendBool = UserInToMq(object.toString());
		if (!sendBool) {
			logger.info("用户入MQ异常");
		}
	}
	
	//将用户信息保存到mq
	private boolean UserInToMq(String user) {
		senderUserToMq.sendMessage(user);
		return true;
	}
	
	//从MQ中消费数据并保存数据库
	public void handlerInsertUser(JSONObject object) {
		User user = JSONObject.toJavaObject(object, User.class);
		int success = userMapper.insertOrder(user);
		if(success > 0){
			//将用户信息保存到分单队列中
			singerUserMq.sendMessage(object.toJSONString());
		}else{
			logger.info("Failed Order Save-- wsf--原始json: " + object);
		}
	}

	public User getUserById(String id) {
		User user = userMapper.getUserById(id);
		
		return user;
	}

}
