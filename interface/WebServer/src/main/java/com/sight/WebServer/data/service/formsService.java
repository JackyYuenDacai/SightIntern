package com.sight.WebServer.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sight.WebServer.data.dao.formsMapper;

@Service
public class formsService {
	@Autowired
	private formsMapper FormsMapper;
	public String getFormById(String id,String soc) {
		String ret = null;
		return ret;
	}
}
