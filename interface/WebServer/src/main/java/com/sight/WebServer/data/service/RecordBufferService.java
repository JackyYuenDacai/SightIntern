package com.sight.WebServer.data.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sight.WebServer.data.entity.RecordBuffer;
import com.sight.WebServer.data.entity.record_entity;
import com.sight.WebServer.utils.General;

@Service
public class RecordBufferService {
	List<RecordBuffer> RecordList = new ArrayList<RecordBuffer>();
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
	public RecordBuffer findByTokenAndRemove(String token) {
		RecordBuffer ret = null;
		for(RecordBuffer RB : RecordList) {
			if(RB.token == token) {
				ret = RB;
				RecordList.remove(RB);
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
	public List<RecordBuffer> findUnattached() {
		List<RecordBuffer> RetList = new ArrayList<RecordBuffer>();
		for(RecordBuffer RB : RecordList) {
			if(RB.id == "UN") {
				RetList.add(RB);
			}
		}
		return RetList;
	}
	public List<RecordBuffer> findOngoingRecord(String id,String location){
		List<RecordBuffer> RetList = new ArrayList<RecordBuffer>();
		if(id == null || id == "") {
			//NOT ACORDING TO ID
			for(RecordBuffer RB : RecordList) {
				if(RB.location == location) {
					RetList.add(RB);
				}
			}
		}else {
			for(RecordBuffer RB : RecordList) {
				if(RB.id == id && RB.location == location) {
					RetList.add(RB);
				}
			}
		}
		
		return RetList;
	}
}
