package com.sight.WebServer.data.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sight.WebServer.data.model.record_master;

@Service
public class recordService {
	//findDoneRecordBy
	//findOngoingRecordBy
	@Autowired
	private RecordBufferService recordBufferService;
	@Autowired
	private usersService UsersService;
	
	public List<record_master> findDoneRecord(String id,String location,String time_start,String time_end){
		List<record_master> ret = new ArrayList<record_master>();
		return ret;
	}
	
}
