package com.sight.WebServer;

import javax.annotation.PostConstruct;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import com.sight.WebServer.WebServerApplication;

@MapperScan(value = "com.sight.WebServer.data.dao")
@SpringBootApplication
public class WebServerApplication {
	@Autowired
	private RedisTemplate redisTemplate = null;
	public static void main(String[] args) {
		SpringApplication.run(WebServerApplication.class, args);
	}
	@PostConstruct
	public void init() {
		initRedisTemplate();
	}
	private void initRedisTemplate () {
		RedisSerializer stringSerializer = redisTemplate.getStringSerializer(); 
		redisTemplate.setKeySerializer (stringSerializer);
		redisTemplate .setHashKeySerializer(stringSerializer);
	}
}