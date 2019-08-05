package com.sight.WebServer.data.model;

import java.util.Date;

public class record_master {
    private String id;

    private String type;

    private String location;

    private Date recordIn;

    private Date recordOut;

    private String data;

    private String token;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public Date getRecordIn() {
        return recordIn;
    }

    public void setRecordIn(Date recordIn) {
        this.recordIn = recordIn;
    }

    public Date getRecordOut() {
        return recordOut;
    }

    public void setRecordOut(Date recordOut) {
        this.recordOut = recordOut;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data == null ? null : data.trim();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }
}