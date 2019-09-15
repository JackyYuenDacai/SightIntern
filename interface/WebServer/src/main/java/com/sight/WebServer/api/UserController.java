package com.sight.WebServer.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sight.WebServer.data.model.users;
import com.sight.WebServer.data.model.users_icon;
import com.sight.WebServer.data.service.usersService;
import com.sight.WebServer.data.service.users_iconService;
import com.sight.WebServer.data.service.users_tokenService;
import com.sight.WebServer.utils.General;

import net.sf.json.JSONObject;

@CrossOrigin
@Controller
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private usersService UsersService;
	@Autowired
	private users_tokenService UsersTokenService;
	@Autowired
	private users_iconService UsersIconService;
	
	@RequestMapping(value = "/user_config")
    @ResponseBody
    public Map<String, Object> user_config(HttpServletRequest request) throws Exception {
		Map<String,Object> ret = new HashMap<String,Object>();
		JSONObject jsonObject = General.getRequest(request.getInputStream());
		JSONObject parameters = JSONObject.fromObject(jsonObject.get("parameters"));
		Map<String,Object> data = new HashMap<String,Object>();
		String type = null; if(parameters.containsKey("type")) type = parameters.getString("type");
		String soc = null;  if(parameters.containsKey("soc")) soc = parameters.getString("soc");
		String id = null;  if(parameters.containsKey("id")) id = parameters.getString("id");
		String name = null;  if(parameters.containsKey("name")) name = parameters.getString("name");
		String role = null;  if(parameters.containsKey("role")) type = parameters.getString("role");
		String icon_id = null;  if(parameters.containsKey("icon_id")) type = parameters.getString("icon_id");
		String pwd = null;  if(parameters.containsKey("pwd")) pwd = parameters.getString("pwd");
		String extra = null;  if(parameters.containsKey("extra")) extra = parameters.getString("extra");
		
		if(type.equals("add")) {

			if(UsersService.getUserById("id")!=null) {
				ret.put("error", 502);
				return ret;
			}

			users User = new users();
			User.setExtra(extra);
			User.setId(id);
			User.setName(name);
			User.setRole(role);
			User.setPwd(pwd);
			User.setSoc(soc);
			UsersService.addUser(User);
			UsersIconService.addUserIcon(id, icon_id);
			
			ret.put("error", 0);
			return ret;
		}else if(type.equals("edit")){
			
			if(name != null) {
				UsersService.updateUserName(id, name);
			}
			if(extra != null) {
				UsersService.updateUserExtra(id, extra);
			}
			if(pwd != null) {
				UsersService.updateUserPwd(id,pwd);
			}
			if(icon_id != null) {
				UsersIconService.updateUserIcon(id, icon_id);
			}
			if(soc != null) {
				//UsersService.updateUserSoc(id,soc);
			}
			ret.put("error", 0);
			return ret;
		}
		else if(type.contentEquals("query")){
			users User = UsersService.getUserById(id);
			if(User==null) {
				ret.put("error", 502);
				return ret;
			}else {
				data.put("id", User.getId());
				data.put("soc", User.getSoc());
				data.put("role", User.getRole());
				data.put("privilege", User.getPrivilege());
				data.put("icon_id", UsersIconService.getUserIconById(id).getIconId());
				data.put("extra", User.getExtra());
				ret.put("data", data);
				ret.put("error", 0);
				
				return ret;
			}
			
		}
		ret.put("error", 502);
		return ret;
	}
	
	@RequestMapping(value = "/link_google")
    @ResponseBody
    public Map<String, Object> LinkGoogle(HttpServletRequest request) throws Exception {
		Map<String,Object> ret = new HashMap<String,Object>();
		ret.put("error", 0);
		return ret;
	}	
	
	@RequestMapping(value = "/search_user")
	@ResponseBody
	public Map<String, Object> SearchUser(HttpServletRequest request) throws Exception {
		Map<String,Object> ret = new HashMap<String,Object>();
		JSONObject jsonObject = General.getRequest(request.getInputStream());
		JSONObject parameters = JSONObject.fromObject(jsonObject.get("parameters"));
		String type = parameters.getString("type");
		String token = jsonObject.getString("token");
		String uid = jsonObject.getString("id");
		
		try {
		List<users> UsersList = null;
		switch(type) {
			case "id":	
				String sid = parameters.getString("id"); 
				UsersList=UsersService.searchUserById(sid);		
				break;
			case "name":
				String sname = parameters.getString("name");
				UsersList=UsersService.searchUserByName(sname);	
				break;
		}
		Map<String,Object> data = new HashMap<String,Object>();
		List<JSONObject> users =  new ArrayList<JSONObject>();
		int upi = UsersService.getUserById(uid).getPrivilege();
		for(users US : UsersList) {
			if(US.getPrivilege()<upi && US.getParentId().contains(uid) == false)
				continue;
			JSONObject u = new JSONObject();
			u.put("id", US.getId());
			u.put("name", US.getName());
			u.put("extra", US.getExtra());
			u.put("role", US.getRole());
			u.put("icon_id", UsersIconService.getUserIconById(US.getId()));
			users.add(u);
		}
		data.put("users", users);
		ret.put("data", data);
		ret.put("error", 0);
		}catch(Exception ex) {
			ret.put("error", 502);
			ret.put("error_msg", ex.getMessage());
		}
		return ret;
	
	}
}
