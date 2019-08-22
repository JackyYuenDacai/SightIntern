package com.sight.WebServer.api;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sight.WebServer.data.dao.usersMapper;
import com.sight.WebServer.data.model.users;
import com.sight.WebServer.data.model.users_token;
import com.sight.WebServer.data.service.usersService;
import com.sight.WebServer.data.service.users_tokenService;
import com.sight.WebServer.interceptor.LoginInterceptor;
import com.sight.WebServer.utils.General;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/test")
public class TestController {
	
	private static final Logger LOG= LoggerFactory.getLogger(LoginInterceptor.class);
	
	@Resource
	private usersService UsersService;
	@Resource
	private users_tokenService UsersTokenService;
	
	@RequestMapping(value = "/print_info")
    @ResponseBody
    public Map<String, Object> printInfo(HttpServletRequest request) throws Exception {
		Map<String,Object> ret = new HashMap<String,Object>();
		ret.put("test", "success");
		Map<String,Object> ab = new HashMap<String,Object>();
		ab.put("ta", "B");
		ret.put("ABB",ab);
		return ret;
	}
	
	@RequestMapping(value = "/validate_token")
	@ResponseBody
    public Map<String, Object> validate_token(HttpServletRequest request) throws Exception {
		Map<String,Object> ret = new HashMap<String,Object>();
    	String soc,id,pwd,token = null,name = null,priority = null;
    	//String params = StreamUtils.streamToString(request.getInputStream());
    	LOG.info("test: token validate");
    	JSONObject jsonObject = General.getRequest(request.getInputStream());
    	id = jsonObject.getString("id");
    	token = jsonObject.getString("token");
    	users_token UsersToken = UsersTokenService.getUserTokenById(id);
    	LOG.info(UsersToken.getToken() + "  " +token);
    	if(UsersToken.getToken() .compareTo(token) == 0) {
    		ret.put("error", 0);
    	}
        else{
        	ret.put("error", 502);
    	}
    	
		return ret;
	}
	
	@RequestMapping(value = "/refresh_token")
	@ResponseBody
    public Map<String, Object> refresh_token(HttpServletRequest request) throws Exception {
		Map<String,Object> ret = new HashMap<String,Object>();
    	String soc,id,pwd,token = null,name = null,priority = null;
    	//String params = StreamUtils.streamToString(request.getInputStream());
    	LOG.info("test: token validate");
    	JSONObject jsonObject = General.getRequest(request.getInputStream());
    	id = jsonObject.getString("id");
    	token = jsonObject.getString("token");
    	users_token UsersToken = UsersTokenService.getUserTokenById(id);
    	if(UsersToken.getToken() == token && UsersToken.getExpire().after(new Date())) {
    		ret.put("error", 0);
    	}
        else{
        	ret.put("error", 502);
    	}
    	
		return ret;
	}
	

}
