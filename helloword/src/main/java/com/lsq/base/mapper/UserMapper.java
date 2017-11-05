package com.lsq.base.mapper;

import java.util.List;

import com.lsq.base.vo.User;

public interface UserMapper {


	List<User> getUserList();

	int insertOrder(User user);

	User getUserById(String id);

}
