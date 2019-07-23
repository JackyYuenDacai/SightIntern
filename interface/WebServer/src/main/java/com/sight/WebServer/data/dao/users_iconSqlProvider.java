package com.sight.WebServer.data.dao;

import com.sight.WebServer.data.model.users_icon;
import org.apache.ibatis.jdbc.SQL;

public class users_iconSqlProvider {

    public String insertSelective(users_icon record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("users_icon");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=VARCHAR}");
        }
        
        if (record.getIconId() != null) {
            sql.VALUES("icon_id", "#{iconId,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(users_icon record) {
        SQL sql = new SQL();
        sql.UPDATE("users_icon");
        
        if (record.getIconId() != null) {
            sql.SET("icon_id = #{iconId,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=VARCHAR}");
        
        return sql.toString();
    }
}