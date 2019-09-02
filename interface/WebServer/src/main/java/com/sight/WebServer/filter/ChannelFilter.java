package com.sight.WebServer.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.sight.WebServer.utils.RequestWrapper;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
@WebFilter(urlPatterns = {"/api/login"})
public class ChannelFilter implements Filter {

	private static final Set<String> EXCLUDE_PATH = Collections.unmodifiableSet(new HashSet<>(
            Arrays.asList(
            		"/api/upload"
            		)));

	
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
 
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    	ServletRequest requestWrapper = null;
    	HttpServletRequest request = (HttpServletRequest) servletRequest;
    	//if(servletRequest.getLocalName()==""
    	String path = request.getRequestURI().substring(request.getContextPath().length()).replaceAll("[/]+$", "");
        boolean excludedPath = EXCLUDE_PATH.contains(path);

        if(!excludedPath)
        if(servletRequest instanceof HttpServletRequest) {
            requestWrapper = new RequestWrapper((HttpServletRequest) servletRequest);
        }
        if(requestWrapper == null) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            filterChain.doFilter(requestWrapper, servletResponse);
        }
        
    }
    @Override
    public void destroy() {
    }
}