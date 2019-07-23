package com.sight.WebServer.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties.Jedis;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/redis")
public class RedisControllerTest {
	@Autowired
	private RedisTemplate redisTemplate = null;
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate = null;
	
	@RequestMapping("/stringAndHash")
	@ResponseBody
    public Object uploadFile(HttpServletRequest request) throws Exception {
		return 0;
	}
}
