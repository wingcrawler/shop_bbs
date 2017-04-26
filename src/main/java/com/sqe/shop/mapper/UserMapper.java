package com.sqe.shop.mapper;

import com.sqe.shop.model.User;

public interface UserMapper extends BaseMapper {

	User findByName(String username);

	User findUserByUsernameAndPassword(User user);

}
