package com.sight.WebServer.api;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.tomcat.jni.FileInfo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;
import com.mongodb.gridfs.GridFSInputFile;
import com.sight.WebServer.data.dao.users_filesSqlProvider;
import com.sight.WebServer.data.model.users_files;
import com.sight.WebServer.utils.General;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/api")
public class ResourceController {
    @Autowired
    private GridFsTemplate gridFsTemplate;
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> uploadFile(HttpServletRequest request) throws Exception {
    	//JSONObject request_para = JSONObject.request.getInputStream().
    	Map<String,Object> ret = new HashMap<String,Object>();
    	JSONObject jsonObject = General.getRequest(request.getInputStream());
    	Part part = request.getPart("file");
        String fileName = part.getSubmittedFileName();
        InputStream ins = part.getInputStream();
        String contentType = part.getContentType();
        
        try {
	        JSONObject parameters = JSONObject.fromObject(jsonObject.get("parameters"));
	        String user_id = parameters.getString("id");
	        String file_data = parameters.getString("file_data");
	        String file_name = user_id + General.RandomPostfix();
	        ObjectId gridFSFile = gridFsTemplate.store(ins, file_name, contentType);
			/*
				"file_type": img/raw/...,
			    "file_data": Stringifyed file data,
			    "id": users' id,
			 */
	        //success
	        users_filesSqlProvider UsersFilesSqlProvider = new users_filesSqlProvider();
	        users_files UsersFiles = new users_files();
	        UsersFiles.setId(file_name);
	        UsersFiles.setPid(user_id);
	        UsersFiles.setFileType(contentType);
	        UsersFilesSqlProvider.insertSelective(UsersFiles);
	        
        }catch(Exception ex) {
        	ret.put("error",502);
        	ret.put("error_msg", ex.getMessage());
        	return ret;
        }
		ret.put("error", 0);
		return ret;

    }
    @SuppressWarnings("deprecation")
	@RequestMapping(value = "/resource")
    @ResponseBody
    public void resource_get(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	Map<String,Object> ret = new HashMap<String,Object>();
    	String fileId = request.getParameter("file_id");
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
            //非IE浏览器的处理：
            fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
        }
        // 通知浏览器进行文件下载
        response.setContentType(gfsfile.getContentType());
        response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");	
    }
}
