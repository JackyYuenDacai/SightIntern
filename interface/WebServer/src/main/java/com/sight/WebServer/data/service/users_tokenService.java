package com.sight.WebServer.data.service;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sight.WebServer.data.dao.usersMapper;
import com.sight.WebServer.data.dao.users_tokenMapper;
import com.sight.WebServer.data.model.*;
@Service
public class users_tokenService {
	@Autowired
	private users_tokenMapper UsersTokenMapper;
	
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
			UsersTokenMapper.insertSelective(record);
			ret = true;
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return ret;
	}
	public users_token getUserTokenById(String id) {
		users_tokenExample UsersTokenExample = new users_tokenExample();
		UsersTokenExample.createCriteria().andIdEqualTo(id);
		return UsersTokenMapper.selectByExample(UsersTokenExample).get(0);
	}
}
