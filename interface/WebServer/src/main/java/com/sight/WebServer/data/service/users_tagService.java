package com.sight.WebServer.data.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sight.WebServer.data.dao.users_iconMapper;
import com.sight.WebServer.data.dao.users_tagMapper;
import com.sight.WebServer.data.model.usersExample;
import com.sight.WebServer.data.model.users_tag;
import com.sight.WebServer.data.model.users_tagExample;

@Service
public class users_tagService {
	@Autowired
	private users_tagMapper UsersTagMapper;
	
	public users_tag getUsersTagByTagId(String tagId) {	
		users_tagExample UsersTagExample = new users_tagExample();
		UsersTagExample.createCriteria().andTagIdEqualTo(tagId);
		List<users_tag> utl = UsersTagMapper.selectByExample(UsersTagExample);
		if(utl.size()>0)
			return utl.get(0);
		else 
			return null;
		
	}
	public List<users_tag> getUsersTagById(String Id) {
		users_tagExample UsersTagExample = new users_tagExample();
		UsersTagExample.createCriteria().andIdEqualTo(Id);
		return UsersTagMapper.selectByExample(UsersTagExample);
	}
	public boolean deleteUsersTagByTagId(String tagId) {
		boolean ret = false;
		try {
		users_tagExample UsersTagExample = new users_tagExample();
		UsersTagExample.createCriteria().andTagIdEqualTo(tagId);
		UsersTagMapper.deleteByExample(UsersTagExample);
		ret = true;
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return ret;
	}
	public boolean addUsersTag(String Id,String type,String tagId) {
		boolean ret = false;
		users_tag UsersTag = new users_tag();
		try {
			UsersTag.setId(Id);
			UsersTag.setTagType(type);
			UsersTag.setTagId(tagId);
			UsersTagMapper.insert(UsersTag);
			ret = true;
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return ret;
	}
}
