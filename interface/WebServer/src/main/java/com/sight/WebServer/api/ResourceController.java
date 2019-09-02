package com.sight.WebServer.api;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.tomcat.jni.FileInfo;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;
import com.mongodb.gridfs.GridFSInputFile;
import com.sight.WebServer.data.dao.users_filesMapper;
import com.sight.WebServer.data.dao.users_filesSqlProvider;
import com.sight.WebServer.data.model.forms;
import com.sight.WebServer.data.model.users_files;
import com.sight.WebServer.data.service.RecordBufferService;
import com.sight.WebServer.data.service.formsService;
import com.sight.WebServer.data.service.usersService;
import com.sight.WebServer.data.service.users_filesService;
import com.sight.WebServer.data.service.users_tokenService;
import com.sight.WebServer.utils.General;

import jdk.internal.jline.internal.Log;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/api")
public class ResourceController {
	
	@Autowired
	formsService FormsService;
	@Autowired
	usersService UsersService;
	@Autowired
	users_tokenService UsersTokenService;
	@Autowired
	users_filesService UsersFilesService;
    @Autowired
    private GridFsTemplate gridFsTemplate;
    @Autowired
    private users_filesMapper Users_FilesMapper;
	
	private static final Logger LOG= LoggerFactory.getLogger(ResourceController.class);
	
	@RequestMapping(value= "/form_config")
	@ResponseBody
	public Map<String,Object> form_config(HttpServletRequest request)throws Exception{
	
		Map<String,Object> ret = new HashMap<String,Object>();
		Map<String,Object> dat = new HashMap<String,Object>();
		JSONObject jsonObject = General.getRequest(request.getInputStream());
		try {
			JSONObject parameters = JSONObject.fromObject(jsonObject.get("parameters"));
			String type = parameters.getString("type");
			String id = null;
			String soc = null;
			String form = null;
			switch(type) {
			case "add":
				id = parameters.getString("id");
				soc = parameters.getString("soc");
				form = parameters.getString("form");
				if(FormsService.addFormById(id, soc, form) == true) {
					ret.put("error",0);
				}else {
					ret.put("error", 502);
					ret.put("error_msg","");
				}
				break;
			case "del":
				id = parameters.getString("id");
				soc = parameters.getString("soc");
				if(FormsService.delFormsById(id, soc) == true) {
					ret.put("error",0);
				}else {
					ret.put("error", 502);
					ret.put("error_msg","");
				}
				break;
			case "change":
				id = parameters.getString("id");
				soc = parameters.getString("soc");
				form = parameters.getString("form");
				if(FormsService.updateFormById(id, soc, form) == true) {
					ret.put("error",0);
				}else {
					ret.put("error", 502);
					ret.put("error_msg","");
				}
		
				break;
			case "list":
				soc = parameters.getString("soc");
				List<JSONObject> fms = new ArrayList<JSONObject>();
				List<forms> Forms = FormsService.getFormsBySoc(soc);
				Forms.forEach( fs -> {
					JSONObject jsono = new JSONObject();
					jsono.put("id", fs.getId());
					jsono.put("soc", fs.getSoc());
					jsono.put("form", fs.getFormData());
					fms.add(jsono);
				});
				dat.put("form", fms);
				break;
			case "query":
				soc = parameters.getString("soc");
				id = parameters.getString("id");
				forms fm = FormsService.getFormById(id, soc);
				if(fm != null) {
					dat.put("form",fm.getFormData());
					ret.put("error",0);
				}else {
					ret.put("error",502);
					ret.put("error_msg","form not found!");
				}
				break;
			}
		}
		catch(Exception ex) {
			ret.put("error", 202);
			ret.put("error_msg",ex.getMessage());
		}
		return ret;
	}
	

    
    @RequestMapping(value = "/upload")
    @ResponseBody
    public Map<String,Object> uploadFile(@RequestParam("id") String id,@RequestParam("token") String token,MultipartFile file){
    	Map<String,Object> ret = new HashMap<String,Object>();
    	Map<String,Object> dat = new HashMap<String,Object>();
    	String user_id = id;
    	try {
    		if(UsersTokenService.getUserTokenById(user_id).getToken() == token)
		    	if (!file.isEmpty()) {  
			        String fileName = file.getOriginalFilename();
			        // get file stream
			        InputStream ins = file.getInputStream();
			        // get file type
			        String contentType = file.getContentType();
			        // store file in mongodb
			        ObjectId objectId = gridFsTemplate.store(ins, fileName, contentType);
			        
			        users_files UsersFiles = new users_files();
			        UsersFiles.setFileId(General.RandomToken());
			        UsersFiles.setId(ObjectId.get().toString());
			        UsersFiles.setPid(user_id);
			        UsersFiles.setFileType(contentType);
			        Users_FilesMapper.insertSelective(UsersFiles);
			        //UsersFilesSqlProvider.insertSelective(UsersFiles);
			        dat.put("file_id", ObjectId.get().toString());
			        ret.put("data", dat);
			        ret.put("error", 0);
		    	}else {
		    		ret.put("error", 502);
		    	}
    		else {
    			ret.put("error", 401);
    		}
    	}catch(Exception ex) {
    		ret.put("error", 502);
    		LOG.error(ex.getMessage());
    	}
    	return ret;
    }
 
    @RequestMapping(value = "/resource")
    @ResponseBody
    public void resource_get(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	String fileId = request.getParameter("file_id");
    	String token = request.getParameter("token");
    	String id = request.getParameter("id");
    	users_files ufs = UsersFilesService.getUsersFilesById(fileId);
    	if(UsersTokenService.getUserTokenById(id).getToken() == token)
    	if(ufs != null) 
    	{
    	
	        Query query = Query.query(Criteria.where("_id").is(fileId));
	        com.mongodb.client.gridfs.model.GridFSFile gfsfile = gridFsTemplate.findOne(query);
	        if (gfsfile == null) {
	            return ;
	        }
	        String fileName = gfsfile.getFilename().replace(",", "");
	        if (request.getHeader("User-Agent").toUpperCase().contains("MSIE") ||
	                request.getHeader("User-Agent").toUpperCase().contains("TRIDENT")
	                || request.getHeader("User-Agent").toUpperCase().contains("EDGE")) {
	            fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
	        } else {
	            //NOT IE BROWSER?
	            fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
	        }
	        //NOTIFY BROWSER TO DOWNLOAD
	        //response.setContentType(gfsfile.getContentType());
	        response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");	
	        GridFsResource gridfsResource = gridFsTemplate.getResource(gfsfile);
	        gridfsResource.getInputStream().transferTo(response.getOutputStream());
    	}
    }
}
