package com.sight.WebServer.data.dao;

import com.sight.WebServer.data.model.users_tag;
import org.apache.ibatis.jdbc.SQL;

public class users_tagSqlProvider {

    public String insertSelective(users_tag record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("users_tag");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=VARCHAR}");
        }
        
        if (record.getTagType() != null) {
            sql.VALUES("tag_type", "#{tagType,jdbcType=VARCHAR}");
        }
        
        if (record.getTagId() != null) {
            sql.VALUES("tag_id", "#{tagId,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }
}