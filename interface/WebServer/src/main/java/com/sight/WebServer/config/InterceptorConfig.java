package com.sight.WebServer.config;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.sight.WebServer.interceptor.LoginInterceptor;
import com.sight.WebServer.interceptor.PrivilegeInterceptor;
import com.sight.WebServer.interceptor.ValidPathInterceptor;

@SuppressWarnings("deprecation")
@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport{
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
		//Execute order are the same as register order.
		registry.addInterceptor(new ValidPathInterceptor())
			.addPathPatterns("/api/**")
			.addPathPatterns("/api/**");
		registry.addInterceptor(new LoginInterceptor())
			.addPathPatterns("/resources/**");
		registry.addInterceptor(new PrivilegeInterceptor())
			.addPathPatterns("/**");
    }
}
