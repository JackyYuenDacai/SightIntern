package com.sight.WebServer.data.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sight.WebServer.data.entity.RecordBuffer;
import com.sight.WebServer.data.entity.record_entity;
import com.sight.WebServer.utils.General;

@Service
public class RecordBufferService {
	List<RecordBuffer> RecordList;
	public RecordBuffer getById(String Id) {
		RecordBuffer ret = null;
		for(RecordBuffer RB : RecordList) {
			if(RB.id == Id) {
				ret = RB;
				break;
			}
		}
		return ret;
	}
	
	public void updateById(String Id,record_entity RecordEntity) {
		RecordBuffer ret = null;
		for(RecordBuffer RB : RecordList) {
			if(RB.id == Id) {
				//EXIST
				
				return;
			}
		}
		//NOT EXIST
		RecordBuffer RE = new RecordBuffer();
		RE.id = Id;
		RE.tag_id = RecordEntity.getTagId();
		RE.soc = RecordEntity.getSoc();
		RE.time = RecordEntity.getTime();
		RE.location = RecordEntity.getLocation();
		RE.token = General.RandomToken();
		RecordList.add(RE);
	}
	public void updateUnattached(record_entity RecordEntity) {
		RecordBuffer RE = new RecordBuffer();
		//TODO: unattached tag id is UN
		RE.id = "UN"; 
		RE.tag_id = RecordEntity.getTagId();
		RE.soc = RecordEntity.getSoc();
		RE.time = RecordEntity.getTime();
		RE.location = RecordEntity.getLocation();
		RE.token = General.RandomToken();
		RecordList.add(RE);
	}
	public void removeOvertime() throws ParseException {
		Date now = new Date();
		for(RecordBuffer RB: RecordList) {
			Date there = General.StringToDate(RB.time);
			if(there.before(General.DateMinusMinutes(now, 25))) {
				RecordList.remove(RB);
			}
		}
	}
}
