package com.sight.WebServer.proc;

import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.sight.WebServer.data.entity.record_entity;
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
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
    	Query query = new Query();
    	query.limit(50);
    	List<record_entity> TagRecords = mongoTemplate.findAllAndRemove(query, record_entity.class);
        
    }
}
