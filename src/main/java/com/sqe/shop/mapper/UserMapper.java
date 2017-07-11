package com.sqe.shop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sqe.shop.model.User;

public interface UserMapper extends BaseMapper {

	User findByName(@Param("username") String username);

	User findUserByUsernameAndPassword(User user);

	User findOwnerUser(@Param("username")String ownerName);

	List<User> findOnlyByName(@Param("username") String username);

}
