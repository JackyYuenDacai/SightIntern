package com.sight.WebServer.data.dao;

import com.sight.WebServer.data.model.users;
import org.apache.ibatis.jdbc.SQL;

public class usersSqlProvider {

    public String insertSelective(users record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("users");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=VARCHAR}");
        }
        
        if (record.getName() != null) {
            sql.VALUES("name", "#{name,jdbcType=VARCHAR}");
        }
        
        if (record.getRole() != null) {
            sql.VALUES("role", "#{role,jdbcType=VARCHAR}");
        }
        
        if (record.getPrivilege() != null) {
            sql.VALUES("privilege", "#{privilege,jdbcType=INTEGER}");
        }
        
        if (record.getExtra() != null) {
            sql.VALUES("extra", "#{extra,jdbcType=VARCHAR}");
        }
        
        if (record.getPwd() != null) {
            sql.VALUES("pwd", "#{pwd,jdbcType=VARCHAR}");
        }
        
        if (record.getSoc() != null) {
            sql.VALUES("soc", "#{soc,jdbcType=VARCHAR}");
        }
        
        if (record.getParentId() != null) {
            sql.VALUES("parent_id", "#{parentId,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(users record) {
        SQL sql = new SQL();
        sql.UPDATE("users");
        
        if (record.getName() != null) {
            sql.SET("name = #{name,jdbcType=VARCHAR}");
        }
        
        if (record.getRole() != null) {
            sql.SET("role = #{role,jdbcType=VARCHAR}");
        }
        
        if (record.getPrivilege() != null) {
            sql.SET("privilege = #{privilege,jdbcType=INTEGER}");
        }
        
        if (record.getExtra() != null) {
            sql.SET("extra = #{extra,jdbcType=VARCHAR}");
        }
        
        if (record.getPwd() != null) {
            sql.SET("pwd = #{pwd,jdbcType=VARCHAR}");
        }
        
        if (record.getSoc() != null) {
            sql.SET("soc = #{soc,jdbcType=VARCHAR}");
        }
        
        if (record.getParentId() != null) {
            sql.SET("parent_id = #{parentId,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=VARCHAR}");
        
        return sql.toString();
    }
}