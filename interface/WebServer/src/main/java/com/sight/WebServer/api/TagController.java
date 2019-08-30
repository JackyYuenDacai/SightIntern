package com.sight.WebServer.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sight.WebServer.data.entity.record_entity;
import com.sight.WebServer.data.model.users_tag;
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
		Map<String,Object> dat = new HashMap<String,Object>();
		JSONObject jsonObject = General.getRequest(request.getInputStream());
		try {
			JSONObject parameters = JSONObject.fromObject(jsonObject.get("parameters"));
			String soc = null;
			String id = null;
			String role = null;
			String tag_type = null;
			String tag_id = null;
			String type = parameters.getString("type");
			switch(type) {
			case "del":
				tag_id = parameters.getString("tag_id");
				UsersTagService.deleteUsersTagByTagId(tag_id);
				ret.put("error", 0);
				return ret;
			case "add":
				tag_id = parameters.getString("tag_id");
				id = parameters.getString("id");
				tag_type = parameters.getString("tag_type");
				if(UsersTagService.getUsersTagByTagId(tag_id)== null) {
					UsersTagService.addUsersTag(id, tag_type, tag_id);
					ret.put("error", 0);
					return ret;
				}
				break;
			case "query":
				id = parameters.getString("id");
				
				List<users_tag> ut = UsersTagService.getUsersTagById(id);
				List<JSONObject> tagList = new ArrayList<JSONObject>();
				ut.forEach( tg ->{
					JSONObject jsono = new JSONObject();
					
					jsono.put("type", tg.getTagType());
					jsono.put("tag_id",tg.getTagId());
					
					tagList.add(jsono);
				});
				dat.put("tags", tagList);
				ret.put("data", dat);
				return ret;
			default:break;
			}

		}catch(Exception ex) {
			ex.printStackTrace();
			ret.put("error_msg", ex.getMessage());
		}
		ret.put("error", 502);
		return ret;
	}
}
