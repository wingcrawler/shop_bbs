package com.sqe.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sqe.shop.mapper.AdminMapper;

@Component  
public class AdminService extends AdapterService implements BaseService {
	
	@Autowired
    AdminMapper AdminMapper;

}
