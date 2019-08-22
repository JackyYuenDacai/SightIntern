package com.sight.WebServer;

import javax.annotation.PostConstruct;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import com.sight.WebServer.WebServerApplication;

@MapperScan(value = "com.sight.WebServer.data.dao")
@ServletComponentScan(basePackages = "com.sight.WebServer.filter")
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
	
	/*
    @Bean
    public Connector connector(){
        Connector connector=new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        connector.setPort(80);
        connector.setSecure(false);
        connector.setRedirectPort(443);
        return connector;
    }

    @Bean
    public TomcatServletWebServerFactory tomcatServletWebServerFactory(Connector connector){
        TomcatServletWebServerFactory tomcat=new TomcatServletWebServerFactory(){
            @Override
            protected void postProcessContext(Context context) {
                SecurityConstraint securityConstraint=new SecurityConstraint();
                securityConstraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection collection=new SecurityCollection();
                collection.addPattern("/*");
                securityConstraint.addCollection(collection);
                context.addConstraint(securityConstraint);
            }
        };
        tomcat.addAdditionalTomcatConnectors(connector);
        return tomcat;
    }
    */
}