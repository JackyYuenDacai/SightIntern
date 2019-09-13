package com.sight.WebServer.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
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

@CrossOrigin
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
	
	private static final Logger LOG= LoggerFactory.getLogger(RecordController.class);
	
	@RequestMapping(value = "/records")
    @ResponseBody
    public Map<String, Object> record_query(HttpServletRequest request) throws Exception {
		Map<String,Object> ret = new HashMap<String,Object>();ret.put("error", 502);
		
		JSONObject jsonObject = General.getRequest(request.getInputStream());
		JSONObject parameters = JSONObject.fromObject(jsonObject.get("parameters"));
		String registered,status,location,name,role,time_start,time_end,id,IfForm;
		try {registered = parameters.getString("registered");}catch(Exception ex) {registered = "true";}
		try {status = parameters.getString("status");}catch(Exception ex) {status = "";}
		try {location = parameters.getString("location");}catch(Exception ex) {location = "";}
		try {name = parameters.getString("name");}catch(Exception ex) {name = "";}
		try {role = parameters.getString("role");}catch(Exception ex) {role = "";}
		try {time_start = parameters.getString("time_start");}catch(Exception ex) {time_start = "";}
		try {time_end = parameters.getString("time_end");}catch(Exception ex) {time_end = "";}
		try {id = parameters.getString("id");}catch(Exception ex) {id = "";}
		try {IfForm = parameters.getString("status");}catch(Exception ex) {IfForm = "";}

		
		if(registered .equals( "true")) {
			if(status .equals( "done")) {
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
					if(IfForm .equals( "") || IfForm .equals(  "true"))
					sto.put("form",RM.getData());
					data.add(sto);
				}
				ret.put("data",data);
				ret.put("error", 0);
				return ret;
			}else
			if(status .equals( "ongoing")) {
				//recordBufferService
				if(registered .equals( "false")) {
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
						LOG.info("records :"+RM.id +" "+RM.token+" "+RM.time);
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
		LOG.info("submit:enter");
		Map<String,Object> ret = new HashMap<String,Object>();
		JSONObject jsonObject = General.getRequest(request.getInputStream());
		JSONObject parameters = jsonObject.getJSONObject("parameters");
		
		try {
		
			String record_token = parameters.getString("record_token");
			String form = parameters.getString("form");
			RecordBuffer RB = recordBufferService.findByTokenAndRemove(record_token);
			// CURRENT TIME AS THE TIME OF STUDENT GO OUT 
			LOG.info("submit record: find record success");
			if(RB!= null) {
			RecordService.submitRecord(RB.id, RB.location, RB.tag_id, RB.time, General.getDateTime(), "", record_token, form);
			
			LOG.info("submit record: success");
			ret.put("error", 0);
			}else {
				ret.put("error",202);
				ret.put("error_msg","no such record/wrong token");
			}
		
		}catch(Exception ex) {
			//LOG.error("error_msg " + ex.getMessage());
			ret.put("error", 502);
			ret.put("error_msg", ex.getMessage());
		}
		return ret;
	}	
}
