package com.sight.WebServer.api;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mongodb.gridfs.GridFS;
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
    @RequestMapping(value = "/upload")
    @ResponseBody
    public Map<String, Object> uploadFile(HttpServletRequest request) throws Exception {
    	//JSONObject request_para = JSONObject.request.getInputStream().
    	Map<String,Object> ret = new HashMap<String,Object>();
    	InputStream inputStreamObject = request.getInputStream();
        BufferedReader streamReader = new BufferedReader(new InputStreamReader(inputStreamObject, "UTF-8"));
        StringBuilder responseStrBuilder = new StringBuilder();
        String inputStr;
        while ((inputStr = streamReader.readLine()) != null)
            responseStrBuilder.append(inputStr);
        try {
	        JSONObject jsonObject = JSONObject.fromObject(responseStrBuilder.toString());
	        JSONObject parameters = JSONObject.fromObject(jsonObject.get("parameters"));
	        String file_type = parameters.getString("file_type");
	        String user_id = parameters.getString("id");
	        String file_data = parameters.getString("file_data");
	        String file_name = user_id + General.RandomPostfix();
	        gridFsTemplate.store(new ByteArrayInputStream(file_data.getBytes()),file_data, user_id + General.RandomPostfix(), file_type);
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
	        UsersFiles.setFileType(file_type);
	        UsersFilesSqlProvider.insertSelective(UsersFiles);
	        
        }catch(Exception ex) {
        	ret.put("error",502);
        	ret.put("error_msg", ex.getMessage());
        	return ret;
        }
    	
		ret.put("error", 0);
		return ret;
    }
}
