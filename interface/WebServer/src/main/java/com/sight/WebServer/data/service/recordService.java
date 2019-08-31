package com.sight.WebServer.data.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sight.WebServer.api.RecordController;
import com.sight.WebServer.data.dao.record_masterMapper;
import com.sight.WebServer.data.model.record_master;
import com.sight.WebServer.utils.General;

@Service
public class recordService {
	//findDoneRecordBy
	//findOngoingRecordBy
	@Autowired
	private RecordBufferService recordBufferService;
	@Autowired
	private usersService UsersService;
	@Autowired
	private record_masterMapper RecordMapper;
	
	private static final Logger LOG= LoggerFactory.getLogger(RecordController.class);
	
	public List<record_master> findDoneRecord(String id,String location,String time_start,String time_end){
		List<record_master> ret = new ArrayList<record_master>();
		return ret;
	}
	
	public int submitRecord(String id,String location,String tag_id,String time_start,String time_end,String type,String record_token,String form) {
		record_master record = new record_master();
		record.setId(id);record.setToken(record_token);
		record.setData(form);
		try {
			record.setLocation(location);
			record.setRecordIn(General.StringToDate(time_start));
			record.setRecordOut(General.StringToDate(time_end));
			record.setType(type);
			RecordMapper.insertSelective(record);
			return 0;
		}catch(Exception ex) {
			LOG.error(ex.getMessage());
			return 502;
		}
	}
	
}
