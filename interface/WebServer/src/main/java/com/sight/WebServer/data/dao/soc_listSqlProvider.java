package com.sight.WebServer.data.dao;

import com.sight.WebServer.data.model.soc_list;
import org.apache.ibatis.jdbc.SQL;

public class soc_listSqlProvider {

    public String insertSelective(soc_list record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("soc_list");
        
        if (record.getSoc() != null) {
            sql.VALUES("soc", "#{soc,jdbcType=VARCHAR}");
        }
        
        if (record.getName() != null) {
            sql.VALUES("name", "#{name,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(soc_list record) {
        SQL sql = new SQL();
        sql.UPDATE("soc_list");
        
        if (record.getName() != null) {
            sql.SET("name = #{name,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("soc = #{soc,jdbcType=VARCHAR}");
        
        return sql.toString();
    }
}