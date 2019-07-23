package com.sight.WebServer.data.dao;

import com.sight.WebServer.data.model.record_child;
import org.apache.ibatis.jdbc.SQL;

public class record_childSqlProvider {

    public String insertSelective(record_child record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("record_child");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=VARCHAR}");
        }
        
        if (record.getParentId() != null) {
            sql.VALUES("parent_id", "#{parentId,jdbcType=VARCHAR}");
        }
        
        if (record.getStudentId() != null) {
            sql.VALUES("student_id", "#{studentId,jdbcType=VARCHAR}");
        }
        
        if (record.getRecordTime() != null) {
            sql.VALUES("record_time", "#{recordTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getData() != null) {
            sql.VALUES("data", "#{data,jdbcType=VARCHAR}");
        }
        
        if (record.getStatus() != null) {
            sql.VALUES("status", "#{status,jdbcType=INTEGER}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(record_child record) {
        SQL sql = new SQL();
        sql.UPDATE("record_child");
        
        if (record.getParentId() != null) {
            sql.SET("parent_id = #{parentId,jdbcType=VARCHAR}");
        }
        
        if (record.getStudentId() != null) {
            sql.SET("student_id = #{studentId,jdbcType=VARCHAR}");
        }
        
        if (record.getRecordTime() != null) {
            sql.SET("record_time = #{recordTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getData() != null) {
            sql.SET("data = #{data,jdbcType=VARCHAR}");
        }
        
        if (record.getStatus() != null) {
            sql.SET("status = #{status,jdbcType=INTEGER}");
        }
        
        sql.WHERE("id = #{id,jdbcType=VARCHAR}");
        
        return sql.toString();
    }
}