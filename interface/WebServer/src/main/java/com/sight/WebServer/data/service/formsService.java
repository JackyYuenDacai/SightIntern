package com.sight.WebServer.data.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sight.WebServer.data.dao.formsMapper;
import com.sight.WebServer.data.model.forms;
import com.sight.WebServer.data.model.formsExample;
import com.sight.WebServer.interceptor.LoginInterceptor;

@Service
public class formsService {
	
	private static final Logger LOG= LoggerFactory.getLogger(formsService.class);
	@Autowired
	private formsMapper FormsMapper;
	public forms getFormById(String id,String soc) {
		String ret = null;
		formsExample FormsExample = new formsExample();
		FormsExample.createCriteria().andIdEqualTo(id).andSocEqualTo(soc);
		try {
			return FormsMapper.selectByExample(FormsExample).get(0);
		}catch(Exception ex) {
			LOG.error(ex.getMessage());
			return null;
		}
	}
	public boolean delFormsById(String id,String soc) {
		String ret = null;
		formsExample FormsExample = new formsExample();
		FormsExample.createCriteria().andSocEqualTo(soc);
		try {
			FormsMapper.deleteByExample(FormsExample);
		}catch(Exception ex) {
			LOG.error(ex.getMessage());
			return false;
		}
		return true;
	}
	public List<forms> getFormsBySoc(String soc){
		String ret = null;
		formsExample FormsExample = new formsExample();
		FormsExample.createCriteria().andSocEqualTo(soc);
		try {
			return FormsMapper.selectByExample(FormsExample);
		}catch(Exception ex) {
			LOG.error(ex.getMessage());
			return new ArrayList<forms>();
		}
	}
	public boolean addFormById(String id,String soc,String form) {
		forms Form = new forms();
		Form.setFormData(form);
		Form.setId(id);
		Form.setSoc(soc);
		try {
			FormsMapper.insertSelective(Form);
		}catch(Exception ex) {
			LOG.error(ex.getMessage());
			return false;
		}
		return true;
	}
	public boolean updateFormById(String id,String soc,String form) {
		forms Form = new forms();
		Form.setId(id);Form.setSoc(soc);Form.setFormData(form);
		formsExample FormsExample = new formsExample();
		FormsExample.createCriteria().andIdEqualTo(id).andSocEqualTo(soc);
		try {
			FormsMapper.updateByExample(Form, FormsExample);
		}catch(Exception ex) {
			LOG.error(ex.getMessage());
			return false;
		}
		return true;
	}
}
