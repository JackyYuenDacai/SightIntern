package com.sight.WebServer.data.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.sight.WebServer.data.entity.RecordBuffer;
import com.sight.WebServer.data.entity.record_entity;
import com.sight.WebServer.data.model.users_tag;

@Service
public class TagBufferService {
	@Autowired
	private usersService UsersService;
	@Autowired
	private users_tokenService UsersTokenService;
	@Autowired
	private users_tagService UsersTagService;
	@Autowired
	MongoTemplate mongoTemplate;
	@Autowired
	RecordBufferService recordBufferService;
	
	
	public void RetrieveTagsBuffer() {
    	Query query = new Query();
    	query.limit(50);
    	List<record_entity> TagRecords = mongoTemplate.findAllAndRemove(query, record_entity.class);
    	for(record_entity RE : TagRecords){
    		users_tag UsersTag = UsersTagService.getUsersTagByTagId(RE.getTagId());
    		if(UsersTag != null) {
    			//ATTACHED TAG
    			recordBufferService.updateById(UsersTag.getId(), RE);
    		}else {
    			//NOT ATTACHED TAG
    			recordBufferService.updateUnattached(RE);
    		}
    	}
	}
	public void RemoveTimedTags() throws ParseException {
		recordBufferService.removeOvertime();
	}
}
