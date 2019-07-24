package com.sight.WebServer.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.sight.WebServer.data.model.users_token;
import com.sight.WebServer.data.service.usersService;
import com.sight.WebServer.data.service.users_tokenService;

import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor{
	
    private static final Logger LOG= LoggerFactory.getLogger(LoginInterceptor.class);
	@Resource
	private usersService UsersService;
	@Resource
	private users_tokenService UsersTokenService;
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        LOG.info("login preHandler");
    	InputStream inputStreamObject = httpServletRequest.getInputStream();
        BufferedReader streamReader = new BufferedReader(new InputStreamReader(inputStreamObject, "UTF-8"));
        StringBuilder responseStrBuilder = new StringBuilder();
        String inputStr;
        while ((inputStr = streamReader.readLine()) != null)
            responseStrBuilder.append(inputStr);
        JSONObject jsonObject = JSONObject.fromObject(responseStrBuilder.toString());
        /*
    	"token": login token,
    	"id": Account id,
         */
        String token = jsonObject.getString("token");
        String id = jsonObject.getString("id");
        users_token UsersToken = UsersTokenService.getUserTokenById(id);
        if(UsersToken.getToken() == token && UsersToken.getExpire().after(new Date()))
        	return true;
        else
        	return false;
    }
 
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        LOG.info("login postHandler");
    }
 
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    	LOG.info("login afterCompletionHandler");
    }
}
