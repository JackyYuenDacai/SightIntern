package com.sight.WebServer.api;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sight.WebServer.data.dao.usersMapper;
import com.sight.WebServer.data.model.users;
import com.sight.WebServer.data.service.usersService;
import com.sight.WebServer.data.service.users_tokenService;
import com.sight.WebServer.interceptor.LoginInterceptor;
import com.sight.WebServer.utils.General;
import com.sight.WebServer.utils.StreamUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/api")
public class LoginController {
	
	@Autowired
	private usersService UsersService;
	@Autowired
	private users_tokenService UsersTokenService;
	
	private static final Logger LOG= LoggerFactory.getLogger(LoginInterceptor.class);
	
    @RequestMapping(value = "/login")
    @ResponseBody
    public Map<String, Object> loginPort(HttpServletRequest request) throws Exception {
    	
    	Map<String,Object> ret = new HashMap<String,Object>();
    	String soc,id,pwd,token = null,name = null,priority = null;
    	//String params = StreamUtils.streamToString(request.getInputStream());
    	LOG.info("login:entry");
    	//dLOG.info("content:"+params);
    	JSONObject jsonObject = General.getRequest(request.getInputStream());
    	
    	LOG.info(jsonObject.toString());
    	
        try {
	    
	        JSONObject parameters = jsonObject;
	        
	        soc = parameters.getString("soc");
	        id = parameters.getString("id");
	        pwd = parameters.getString("pwd");
	        token = null;
	        users RequiredUser = UsersService.getUserById(id);
	        if(RequiredUser == null) {
	        	ret.put("error", 502);
	        	ret.put("error_msg", "Wrong id/password");
	        	LOG.info("login:wrong id/pwd");
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
	        	LOG.info("login:wrong id/pwd");
	        }
        }
        catch(Exception ex) {
	        	ret.put("error", 502);
	        	ret.put("error_msg", ex.getMessage());
	        	ex.printStackTrace();
	        	LOG.info(ex.getMessage());
        }
        return ret;
    }
    
	@RequestMapping(value = "/token_update")
    @ResponseBody
    public Map<String, Object> token_update(HttpServletRequest request) throws Exception {
    	Map<String,Object> ret = new HashMap<String,Object>();
    	String soc,id,pwd,token = null,name = null,priority = null;
    	JSONObject jsonObject = General.getRequest(request.getInputStream());
	    JSONObject parameters = JSONObject.fromObject(jsonObject.get("parameters"));
	    //TODO: update by example
		return ret;
	}	
}
