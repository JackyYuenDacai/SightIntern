package com.sight.WebServer.data.service;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sight.WebServer.data.dao.usersMapper;
import com.sight.WebServer.data.dao.users_tokenMapper;
import com.sight.WebServer.data.model.*;
import com.sight.WebServer.interceptor.LoginInterceptor;

import jdk.internal.jline.internal.Log;

@Service
public class users_tokenService {
	
	@Autowired
	private users_tokenMapper UsersTokenMapper;
	
	private static final Logger LOG= LoggerFactory.getLogger(users_tokenService.class);
	
	public boolean addUsersToken(users_token UsersToken) {
		boolean ret = false;
		try {
			UsersTokenMapper.insertSelective(UsersToken);
			ret = true;
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return ret;
	}
	public boolean addUsersToken(String id,String token,Date expire) {
		boolean ret = false;
		users_token record = new users_token();
		record.setId(id);
		record.setToken(token);
		record.setExpire(expire);
		try {
			UsersTokenMapper.deleteByPrimaryKey(id);
			UsersTokenMapper.insertSelective(record);
			ret = true;
		}catch(Exception ex) {
			LOG.info(ex.getMessage());
		}
		return ret;
	}
	public boolean IsTokenExist(String token) {
		users_tokenExample UsersTokenExample = new users_tokenExample();
		UsersTokenExample.createCriteria().andTokenEqualTo(token);
		return !(UsersTokenMapper.selectByExample(UsersTokenExample).size() == 0);
	}
	public users_token getUserTokenById(String id) {
		//users_tokenExample UsersTokenExample = new users_tokenExample();
		//UsersTokenExample.createCriteria().and;
		
		return UsersTokenMapper.selectByPrimaryKey(id);
	}
}
