package com.sight.WebServer.data.service;

import java.util.Date;
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
	public boolean addUser(users User) {
		boolean ret = false;
		try {
			UsersMapper.insertSelective(User);
			ret = true;
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return ret;
	}
	public boolean updateUserName(String id, String name) {
		boolean ret = false;
		try {
			usersExample UsersExample = new usersExample();
			UsersExample.createCriteria().andIdEqualTo(id);
			users User = UsersMapper.selectByExample(UsersExample).get(0);
			User.setName(name);
			UsersMapper.updateByExampleSelective(User, UsersExample);
			ret = true;
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return ret;
	}
	public boolean updateUserExtra(String id, String extra) {
		boolean ret = false;
		try {
			usersExample UsersExample = new usersExample();
			UsersExample.createCriteria().andIdEqualTo(id);
			users User = UsersMapper.selectByExample(UsersExample).get(0);
			User.setExtra(extra);
			UsersMapper.updateByExampleSelective(User, UsersExample);
			ret = true;
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return ret;
	}
	public boolean updateUserPwd(String id, String pwd) {
		boolean ret = false;
		try {
			usersExample UsersExample = new usersExample();
			UsersExample.createCriteria().andIdEqualTo(id);
			users User = UsersMapper.selectByExample(UsersExample).get(0);
			User.setPwd(pwd);
			UsersMapper.updateByExampleSelective(User, UsersExample);
			ret = true;
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return ret;
	}
}
