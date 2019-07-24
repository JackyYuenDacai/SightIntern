package com.sight.WebServer.api;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sight.WebServer.data.dao.usersMapper;
import com.sight.WebServer.data.model.users;
import com.sight.WebServer.data.service.usersService;
import com.sight.WebServer.data.service.users_tokenService;
import com.sight.WebServer.utils.General;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/api")
public class LoginController {
	
	@Resource
	private usersService UsersService;
	@Resource
	private users_tokenService UsersTokenService;
	
    @RequestMapping(value = "/login")
    @ResponseBody
    public Map<String, Object> uploadFile(HttpServletRequest request) throws Exception {
    	
    	Map<String,Object> ret = new HashMap<String,Object>();
    	String soc,id,pwd,token = null,name = null,priority = null;
    	InputStream inputStreamObject = request.getInputStream();
        BufferedReader streamReader = new BufferedReader(new InputStreamReader(inputStreamObject, "UTF-8"));
        StringBuilder responseStrBuilder = new StringBuilder();
        String inputStr;
        while ((inputStr = streamReader.readLine()) != null)
            responseStrBuilder.append(inputStr);
        try {
	        JSONObject jsonObject = JSONObject.fromObject(responseStrBuilder.toString());
	        JSONObject parameters = JSONObject.fromObject(jsonObject.get("parameters"));
	        soc = parameters.getString("soc");
	        id = parameters.getString("id");
	        pwd = parameters.getString("pwd");
	        token = null;
	        users RequiredUser = UsersService.getUserById(id);
	        if(RequiredUser == null) {
	        	ret.put("error", 502);
	        	ret.put("error_msg", "Wrong id/password");
	        }
	        else if(RequiredUser.getPwd()==pwd) {
	        	token = General.RandomToken();
	        	name = RequiredUser.getName();
	        	priority = RequiredUser.getRole();
	        	UsersTokenService.addUsersToken(id, token, General.getTokenExpireDate());
	        	ret.put("error", 0);
	        	Map<String,Object> data = new  HashMap<String,Object>();
	        	data.put("token", token);
	        	data.put("name", name);
	        	data.put("privilege", priority);
	        	ret.put("data", data);
	        }else {
	        	ret.put("error", 502);
	        	ret.put("error_msg", "Wrong id/password");
	        }
        }
        catch(Exception ex) {
	        	ret.put("error", 502);
	        	ret.put("error_msg", ex.getMessage());
        }
        return ret;
    }
}
