package com.sight.WebServer.data.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Document(collection = "record")
public class record_entity {
	
	 	@Id
	    private String id;
	 	@Field("token")
	 	private String token;
	 	@Field("location")
	 	private String location;
	 	@Field("soc")
	 	private String soc;
	 	@Field("tag_id")
	 	private String tag_id;
	 	@Field("time")
	 	private String time;
	 	
	 	public String getLocation() {
	 		return location;
	 	}
	 	public String getSoc() {
	 		return soc;
	 	}
	 	public String getTagId() {
	 		return tag_id;
	 	}
	 	public String getTime() {
	 		return time;
	 	}
	 	public void setLocation(String Location) {
	 		location = Location;
	 	}
	 	public void setSoc(String Soc) {
	 		soc = Soc;
	 	}
	 	public void setTagId(String TagId) {
	 		tag_id = TagId;
	 	}
	 	public void setTime(String Time) {
	 		time = Time;
	 	}
/*
 * record buffer
 * location
 * soc
 * tag id
 * 
 * 
 * 
 */

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

}