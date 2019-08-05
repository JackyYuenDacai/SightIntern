package com.sight.WebServer.api;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sight.WebServer.data.entity.record_entity;
import com.sight.WebServer.data.service.usersService;
import com.sight.WebServer.data.service.users_tagService;
import com.sight.WebServer.data.service.users_tokenService;
import com.sight.WebServer.utils.General;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/api")
public class TagController {
	
	@Autowired
	private usersService UsersService;
	@Autowired
	private users_tokenService UsersTokenService;
	@Autowired
	private users_tagService UsersTagService;
	@Autowired
	MongoTemplate mongoTemplate;
	
	@RequestMapping(value = "/tag_scanned")
    @ResponseBody
    public Map<String, Object> tag_scanned(HttpServletRequest request) throws Exception {
		Map<String,Object> ret = new HashMap<String,Object>();
		JSONObject jsonObject = General.getRequest(request.getInputStream());
		JSONObject parameters = JSONObject.fromObject(jsonObject.get("parameters"));
		
		record_entity RecordEntity = new record_entity();
		try {
			String location = parameters.getString("location"),
					soc = parameters.getString("soc"),
					tag_id = parameters.getString("tag_id"),
					time = parameters.getString("time");
			
			RecordEntity.setId(General.RandomToken());
			RecordEntity.setLocation(location);
			RecordEntity.setSoc(soc);
			RecordEntity.setTagId(tag_id);
			RecordEntity.setTime(time);
			mongoTemplate.save(RecordEntity);
			ret.put("error", 0);
			return ret;
		}catch(Exception ex) {
			ex.printStackTrace();
			ret.put("error", 502);
			
			return ret;
		}

	}
	
	@RequestMapping(value = "/tag_config")
    @ResponseBody
    public Map<String, Object> tag_config(HttpServletRequest request) throws Exception {
		Map<String,Object> ret = new HashMap<String,Object>();
		JSONObject jsonObject = General.getRequest(request.getInputStream());
		try {
			JSONObject parameters = JSONObject.fromObject(jsonObject.get("parameters"));
			String soc = parameters.getString("soc");
			String id = parameters.getString("id");
			String role = parameters.getString("role");
			String tag_type = parameters.getString("tag_type");
			String tag_id = parameters.getString("tag_id");
			String type = parameters.getString("type");
			if(type == "del") {
				UsersTagService.deleteUsersTagByTagId(tag_id);
				ret.put("error", 0);
				return ret;
			}else 
			if(type == "add") {
				if(UsersTagService.getUsersTagByTagId(tag_id)== null) {
					UsersTagService.addUsersTag(id, tag_type, tag_id);
					ret.put("error", 0);
					return ret;
				}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		ret.put("error", 502);
		return ret;
	}
}
