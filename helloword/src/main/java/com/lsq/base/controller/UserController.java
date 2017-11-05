package com.lsq.base.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.lsq.base.service.UserService;
import com.lsq.base.vo.User;
import com.lsq.util.StringUtil;

@RestController
@RequestMapping(value="/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@ApiOperation(value="获取用户列表",notes="用户列表")
	@RequestMapping(value="/userList", method = RequestMethod.POST)
	public List<User> getUserList(){
		List<User> list = new ArrayList<User>();
		list=userService.getUserList();
		return list;
	}
	
	@ApiOperation(value="根据ID获取用户",notes="ID获取用户")
	@ApiImplicitParam(name = "data", value = "用户ID字符json数据", required = true, dataType = "String")
	@RequestMapping(value="/getUserById", method = RequestMethod.POST)
	public User getUserById(@RequestBody String data){
		if(StringUtil.isNotBlank(data)){
			JSONObject object = JSONObject.parseObject(data);
			if(object.containsKey("id")){
				User user = userService.getUserById(object.getString("id"));
				return user;
			}
		}
		return null;
	}
	
	@ApiOperation(value="新增用户",notes="新增用户")
	@ApiImplicitParam(name = "data", value = "用户详细字符json数据", required = true, dataType = "String")
	@RequestMapping(value="/insertUser", method = RequestMethod.POST)
	public void insertUser(@RequestBody String data){
		if(StringUtil.isNotBlank(data)){
			JSONObject object = JSONObject.parseObject(data);
			userService.insertUser(object);
		}
	}
	
	
}
