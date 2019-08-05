package com.sight.WebServer.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class recordService {
	//findDoneRecordBy
	//findOngoingRecordBy
	@Autowired
	private RecordBufferService recordBufferService;
	
}
