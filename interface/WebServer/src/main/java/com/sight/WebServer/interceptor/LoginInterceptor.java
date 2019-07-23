package com.sight.WebServer.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor{
	
    private static final Logger LOG= LoggerFactory.getLogger(LoginInterceptor.class);
    
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
        if(true) // valid token
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
