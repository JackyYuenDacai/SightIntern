package com.sight.WebServer.api;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sight.WebServer.data.entity.RecordBuffer;
import com.sight.WebServer.data.service.RecordBufferService;
import com.sight.WebServer.data.service.recordService;
import com.sight.WebServer.data.service.usersService;
import com.sight.WebServer.data.service.users_tokenService;
import com.sight.WebServer.utils.General;

import net.sf.json.JSONObject;
@Controller
@RequestMapping("/api")
public class RecordController {
	
	@Autowired
	private usersService UsersService;
	@Autowired
	private users_tokenService UsersTokenService;
	@Autowired
	private RecordBufferService recordBufferService;
	@Autowired
	private recordService RecordService;
	
	@RequestMapping(value = "/records")
    @ResponseBody
    public Map<String, Object> record_query(HttpServletRequest request) throws Exception {
		Map<String,Object> ret = new HashMap<String,Object>();
		JSONObject jsonObject = General.getRequest(request.getInputStream());
		JSONObject parameters = JSONObject.fromObject(jsonObject.get("parameters"));
		String registered = parameters.getString("registered");
		String status = parameters.getString("status");
		String location = parameters.getString("location");
		String name = parameters.getString("name");
		String role = parameters.getString("role");
		if(registered == "true") {
			if(status == "done") {
				//RecordService
			}else
			if(status =="ongoing") {
				//recordBufferService
				
				
			}
		}else {
			List<RecordBuffer> recordList = recordBufferService.findUnattached();
		}
		ret.put("error", 0);
		return ret;
	}
	
	@RequestMapping(value = "/submit")
    @ResponseBody
    public Map<String, Object> submit_record(HttpServletRequest request) throws Exception {
		Map<String,Object> ret = new HashMap<String,Object>();
		JSONObject jsonObject = General.getRequest(request.getInputStream());
		JSONObject parameters = jsonObject.getJSONObject("parameters");
		String record_token = parameters.getString("record_token");
		JSONObject form = parameters.getJSONObject("form");
		RecordBuffer RB = recordBufferService.findByTokenAndRemove(record_token);
		Date now = new Date();// CURRENT TIME AS THE TIME OF STUDENT GO OUT 
		
		
		ret.put("error", 0);
		return ret;
	}	
}
