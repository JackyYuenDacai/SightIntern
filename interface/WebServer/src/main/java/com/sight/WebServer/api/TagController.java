package com.sight.WebServer.api;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api")
public class TagController {
	@RequestMapping(value = "/tag_scanned")
    @ResponseBody
    public Map<String, Object> tag_scanned(HttpServletRequest request) throws Exception {
		Map<String,Object> ret = new HashMap<String,Object>();
		ret.put("error", 0);
		return ret;
	}
}
