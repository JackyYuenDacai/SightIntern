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


		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

}