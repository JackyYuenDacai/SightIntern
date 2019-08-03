package com.sight.WebServer.api;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sight.WebServer.utils.General;

import net.sf.json.JSONObject;
@Controller
@RequestMapping("/api")
public class RecordController {
	@RequestMapping(value = "/records")
    @ResponseBody
    public Map<String, Object> record_query(HttpServletRequest request) throws Exception {
		Map<String,Object> ret = new HashMap<String,Object>();
		JSONObject jsonObject = General.getRequest(request.getInputStream());
		JSONObject parameters = JSONObject.fromObject(jsonObject.get("parameters"));
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
