package com.sight.WebServer.data.dao;

import com.sight.WebServer.data.model.record_master;
import org.apache.ibatis.jdbc.SQL;

public class record_masterSqlProvider {

    public String insertSelective(record_master record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("record_master");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=VARCHAR}");
        }
        
        if (record.getType() != null) {
            sql.VALUES("type", "#{type,jdbcType=VARCHAR}");
        }
        
        if (record.getLocation() != null) {
            sql.VALUES("location", "#{location,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(record_master record) {
        SQL sql = new SQL();
        sql.UPDATE("record_master");
        
        if (record.getType() != null) {
            sql.SET("type = #{type,jdbcType=VARCHAR}");
        }
        
        if (record.getLocation() != null) {
            sql.SET("location = #{location,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=VARCHAR}");
        
        return sql.toString();
    }
}