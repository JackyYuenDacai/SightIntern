package com.sight.WebServer.data.service;
 
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sight.WebServer.data.dao.usersMapper;
import com.sight.WebServer.data.dao.users_iconMapper;
import com.sight.WebServer.data.dao.users_tokenMapper;
import com.sight.WebServer.data.model.*;
@Service
public class users_iconService {
	@Autowired
	private users_iconMapper UsersIconMapper;
	
	public boolean updateUserIcon(String id, String iconId) {
		boolean ret = false;
		try {
			users_iconExample UsersIconExample = new users_iconExample();
			UsersIconExample.createCriteria().andIdEqualTo(id);
			users_icon UsersIcon = UsersIconMapper.selectByExample(UsersIconExample).get(0);
			UsersIcon.setIconId(iconId);
			UsersIconMapper.updateByExampleSelective(UsersIcon, UsersIconExample);
			ret = true;
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return ret;
	}
	
	public boolean addUserIcon(String id,String iconId) {
		boolean ret = false;
		try {
			users_icon UsersIcon = new users_icon();
			UsersIcon.setIconId(iconId);
			UsersIcon.setId(id);
			UsersIconMapper.insertSelective(UsersIcon);
			ret = true;
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return ret;
	}
}
