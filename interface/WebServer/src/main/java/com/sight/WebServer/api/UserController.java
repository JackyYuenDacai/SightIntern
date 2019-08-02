package com.sight.WebServer.api;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("/api")
public class UserController {
	@RequestMapping(value = "/user_config")
    @ResponseBody
    public Map<String, Object> user_config(HttpServletRequest request) throws Exception {
		Map<String,Object> ret = new HashMap<String,Object>();
		ret.put("error", 0);
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
