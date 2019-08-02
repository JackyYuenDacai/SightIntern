package com.sight.WebServer.api;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("/api")
public class RecordController {
	@RequestMapping(value = "/records")
    @ResponseBody
    public Map<String, Object> record_query(HttpServletRequest request) throws Exception {
		Map<String,Object> ret = new HashMap<String,Object>();
		ret.put("error", 0);
		return ret;
	}
	
	@RequestMapping(value = "/submit")
    @ResponseBody
    public Map<String, Object> submit_record(HttpServletRequest request) throws Exception {
		Map<String,Object> ret = new HashMap<String,Object>();
		ret.put("error", 0);
		return ret;
	}	
}
