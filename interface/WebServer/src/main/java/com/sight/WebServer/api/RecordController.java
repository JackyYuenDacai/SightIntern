package com.sight.WebServer.api;

import java.util.ArrayList;
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
import com.sight.WebServer.data.model.record_master;
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
		Map<String,Object> ret = new HashMap<String,Object>();ret.put("error", 502);
		
		JSONObject jsonObject = General.getRequest(request.getInputStream());
		JSONObject parameters = JSONObject.fromObject(jsonObject.get("parameters"));
		String registered = parameters.getString("registered");
		String status = parameters.getString("status");
		String location = parameters.getString("location");
		String name = parameters.getString("name");
		String role = parameters.getString("role");
		String time_start = parameters.getString("time_start");
		String time_end = parameters.getString("time_end");
		String id = jsonObject.getString("id");
		String IfForm = parameters.getString("if_form");
		if(registered == "true") {
			if(status == "done") {
				//RecordService
				List<record_master> Records = RecordService.findDoneRecord(id, location, time_start, time_end);
				List<Map<String,String>> data = new  ArrayList<Map<String,String>>();
				for(record_master RM : Records) {
					Map<String,String> sto = new  HashMap<String,String>();
					sto.put("id", RM.getId());
					sto.put("time_start", General.DateToString(RM.getRecordIn()));
					sto.put("time_end", General.DateToString(RM.getRecordOut()));
					sto.put("record_token", RM.getToken());
					sto.put("location", RM.getLocation());
					if(IfForm == null || IfForm == "true")
					sto.put("form",RM.getData());
					data.add(sto);
				}
				ret.put("data",data);
				ret.put("error", 0);
				return ret;
			}else
			if(status =="ongoing") {
				//recordBufferService
				if(registered == "false") {
					//NOT REGISTERED
					List<RecordBuffer> Records = recordBufferService.findUnattached();
					List<Map<String,String>> data = new  ArrayList<Map<String,String>>();
					for(RecordBuffer RM : Records) {
						Map<String,String> sto = new  HashMap<String,String>();
						sto.put("time_start", RM.time);
						sto.put("tag_id", RM.tag_id);
						sto.put("tag_type", RM.tag_type);
						sto.put("record_token", RM.token);
						sto.put("location", RM.location);
						data.add(sto);
					}
					ret.put("data",data);
					ret.put("error", 0);
					return ret;
				}else {
					//REGISTERED
					List<RecordBuffer> Records = recordBufferService.findOngoingRecord(id,location);
					//ONLY CARE ABOUT ID AND LOCATION NOW
					List<Map<String,String>> data = new  ArrayList<Map<String,String>>();
					for(RecordBuffer RM : Records) {
						Map<String,String> sto = new  HashMap<String,String>();
						sto.put("id", RM.id);
						sto.put("name",UsersService.getUserById(RM.id).getName());
						sto.put("time_start", RM.time);
						sto.put("tag_id", RM.tag_id);
						sto.put("tag_type", RM.tag_type);
						sto.put("record_token", RM.token);
						sto.put("location", RM.location);
						data.add(sto);
					}
					ret.put("data",data);
					ret.put("error", 0);
					return ret;
				}
				
				
			}
		}else {
			List<RecordBuffer> Records = recordBufferService.findUnattached();
			List<Map<String,String>> data = new  ArrayList<Map<String,String>>();
			for(RecordBuffer RM : Records) {
				Map<String,String> sto = new  HashMap<String,String>();
				sto.put("time_start", RM.time);
				sto.put("tag_id", RM.tag_id);
				sto.put("tag_type", RM.tag_type);
				sto.put("record_token", RM.token);
				sto.put("location", RM.location);
				data.add(sto);
			}
			ret.put("data",data);
			ret.put("error", 0);
			return ret;
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
