package com.sight.WebServer.data.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sight.WebServer.data.dao.usersMapper;
import com.sight.WebServer.data.model.*;
@Service
public class usersService {
	@Autowired
	private usersMapper UsersMapper;
	public List<users> getUserByName(String name){
		usersExample UsersExample = new usersExample();
		UsersExample.createCriteria().andNameEqualTo(name);
		return UsersMapper.selectByExample(UsersExample);
	}
	
	public users getUserById(String id) {
		usersExample UsersExample = new usersExample();
		UsersExample.createCriteria().andIdEqualTo(id);
		return UsersMapper.selectByExample(UsersExample).get(0);
	}
}
