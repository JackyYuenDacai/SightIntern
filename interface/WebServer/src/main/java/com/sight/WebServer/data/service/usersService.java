package com.sight.WebServer.data.service;

import java.util.ArrayList;
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
	public List<users> searchUserById(String id) {
		usersExample UsersExample = new usersExample();
		UsersExample.createCriteria().andIdLike("%"+id+"%");
		return UsersMapper.selectByExample(UsersExample);
	}
	public List<users> searchUserByName(String name) {
		usersExample UsersExample = new usersExample();
		UsersExample.createCriteria().andNameLike("%"+name+"%");
		return UsersMapper.selectByExample(UsersExample);
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
	public List<String> getParents(String id,String soc){
		List<String>ret = new ArrayList<String>();
		try {
			usersExample UsersExample = new usersExample();
			UsersExample.createCriteria().andIdEqualTo(id).andSocEqualTo(soc);
			users User = UsersMapper.selectByExample(UsersExample).get(0);
			String buf = User.getParentId();
			for(String a: buf.split(",")) {
				ret.add(a);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return ret;
	}
	public boolean setParentId(String id,String soc,String parent_id) {
		boolean ret = false;
		try {
			usersExample UsersExample = new usersExample();
			UsersExample.createCriteria().andIdEqualTo(id);
			users User = UsersMapper.selectByExample(UsersExample).get(0);
			User.setParentId(parent_id);
			UsersMapper.updateByExampleSelective(User, UsersExample);
			ret = true;
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return ret;
	}
	public boolean delParentId(String id,String soc) {
		return setParentId(id,soc,"");
	}
}
