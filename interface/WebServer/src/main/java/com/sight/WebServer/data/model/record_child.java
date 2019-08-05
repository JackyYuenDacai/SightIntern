package com.sight.WebServer.data.model;

import java.util.Date;

public class record_child {
    private String id;

    private String parentToken;

    private String studentId;

    private Date recordTime;

    private Integer childStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getParentToken() {
        return parentToken;
    }

    public void setParentToken(String parentToken) {
        this.parentToken = parentToken == null ? null : parentToken.trim();
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId == null ? null : studentId.trim();
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }

    public Integer getChildStatus() {
        return childStatus;
    }

    public void setChildStatus(Integer childStatus) {
        this.childStatus = childStatus;
    }
}