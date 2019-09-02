package com.sight.WebServer.data.service;
 
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sight.WebServer.data.dao.usersMapper;
import com.sight.WebServer.data.dao.users_filesMapper;
import com.sight.WebServer.data.dao.users_iconMapper;
import com.sight.WebServer.data.dao.users_tokenMapper;
import com.sight.WebServer.data.model.*;
@Service
public class users_filesService{
	@Autowired 
	private users_filesMapper UsersFileMapper;
	
	public users_files getUsersFilesById(String id) {
		List<users_files> ret = null;
		users_filesExample UsersFilesExample = new users_filesExample();
		UsersFilesExample.createCriteria().andIdEqualTo(id);
		ret = UsersFileMapper.selectByExample(UsersFilesExample);
		if(ret.size()>=1)
			return ret.get(0);
		else
			return null;
	}
	
}