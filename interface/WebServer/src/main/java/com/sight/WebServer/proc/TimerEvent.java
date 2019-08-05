package com.sight.WebServer.proc;

import java.text.ParseException;
import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.sight.WebServer.data.entity.record_entity;
import com.sight.WebServer.data.service.TagBufferService;
import com.sight.WebServer.data.service.usersService;
import com.sight.WebServer.data.service.users_tagService;
import com.sight.WebServer.data.service.users_tokenService;

public class TimerEvent extends QuartzJobBean{
	@Autowired
	private usersService UsersService;
	@Autowired
	private users_tokenService UsersTokenService;
	@Autowired
	private users_tagService UsersTagService;
	@Autowired
	MongoTemplate mongoTemplate;
	@Autowired 
	TagBufferService tagBufferService;
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
    	try {
    		
    		tagBufferService.RetrieveTagsBuffer();
			tagBufferService.RemoveTimedTags();
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    } 
}
