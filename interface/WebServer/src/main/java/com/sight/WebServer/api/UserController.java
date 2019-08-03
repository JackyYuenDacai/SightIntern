package com.sight.WebServer.api;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sight.WebServer.data.model.users;
import com.sight.WebServer.data.service.usersService;
import com.sight.WebServer.data.service.users_tokenService;
import com.sight.WebServer.utils.General;

import net.sf.json.JSONObject;
@Controller
@RequestMapping("/api")
public class UserController {
	
	@Resource
	private usersService UsersService;
	@Resource
	private users_tokenService UsersTokenService;
	
	@RequestMapping(value = "/user_config")
    @ResponseBody
    public Map<String, Object> user_config(HttpServletRequest request) throws Exception {
		Map<String,Object> ret = new HashMap<String,Object>();
		JSONObject jsonObject = General.getRequest(request.getInputStream());
		JSONObject parameters = JSONObject.fromObject(jsonObject.get("parameters"));
		String type = parameters.getString("type");
		if(type == "add") {
			String soc = parameters.getString("soc");
			String id = parameters.getString("id");
			if(UsersService.getUserById("id")!=null) {
				ret.put("error", 502);
				return ret;
			}
			String name = parameters.getString("name");
			String role = parameters.getString("role");
			String icon_id = parameters.getString("icon_id");
			String pwd = parameters.getString("pwd");
			String extra = parameters.getString("extra");
			users User = new users();
			User.setExtra(extra);
			User.setId(id);
			User.setName(name);
			User.setRole(role);
			User.setPwd(pwd);
			
			UsersService.addUser(User);
			ret.put("error", 0);
			return ret;
		}else if(type == "edit"){
			
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
}
