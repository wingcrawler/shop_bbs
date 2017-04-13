package com.sqe.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sqe.shop.mapper.UserMapper;

@Component  
public class UserService extends AdapterService implements BaseService {
	
	@Autowired
    UserMapper UserMapper;

}
