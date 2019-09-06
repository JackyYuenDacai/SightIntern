package com.sight.WebServer.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.sight.WebServer.data.model.users_token;
import com.sight.WebServer.data.service.usersService;
import com.sight.WebServer.data.service.users_tokenService;
import com.sight.WebServer.utils.RequestWrapper;

import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor{
	
    private static final Logger LOG= LoggerFactory.getLogger(LoginInterceptor.class);
	@Resource
	private usersService UsersService;
	@Resource
	private users_tokenService UsersTokenService;
	
	private static final Set<String> EXCLUDE_PATH = Collections.unmodifiableSet(new HashSet<>(
            Arrays.asList(
            		"/api/upload"
            		)));
	
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        
        RequestWrapper requestWrapper = new RequestWrapper(httpServletRequest);
        String body = requestWrapper.getBody();
        LOG.info("login preHandler: "+body);
        JSONObject jsonObject = JSONObject.fromObject(body);
        JSONObject ret = new JSONObject();
    	String path = httpServletRequest.getRequestURI().substring(httpServletRequest.getContextPath().length()).replaceAll("[/]+$", "");
        boolean excludedPath = EXCLUDE_PATH.contains(path);
        /*
    	"token": login token,
    	"id": Account id,
         */
        if(excludedPath)return true;
        String token = jsonObject.getString("token");
        String id = jsonObject.getString("id");
        users_token UsersToken = UsersTokenService.getUserTokenById(id);
        if(UsersToken.getToken() .compareTo(token) == 0 && UsersToken.getExpire().after(new Date())) {
        	LOG.info("login interceptor: valid");
        	return true;
        }
        else {
        	ret.put("error", 401);
        	httpServletResponse.getOutputStream().write(ret.toString().getBytes());
        	return false;
        }
        
    }
 
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        LOG.info("login postHandler");
        RequestWrapper requestWrapper = new RequestWrapper(httpServletRequest);
        String body = requestWrapper.getBody();
        JSONObject jsonObject = JSONObject.fromObject(body);
    }
 
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    	LOG.info("login afterCompletionHandler");
        RequestWrapper requestWrapper = new RequestWrapper(httpServletRequest);
        String body = requestWrapper.getBody();
        JSONObject jsonObject = JSONObject.fromObject(body);
    }
}
