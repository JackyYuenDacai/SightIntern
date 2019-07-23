package com.sight.WebServer.data.dao;

import com.sight.WebServer.data.model.users_files;
import org.apache.ibatis.jdbc.SQL;

public class users_filesSqlProvider {

    public String insertSelective(users_files record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("users_files");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=VARCHAR}");
        }
        
        if (record.getPid() != null) {
            sql.VALUES("pid", "#{pid,jdbcType=VARCHAR}");
        }
        
        if (record.getFileType() != null) {
            sql.VALUES("file_type", "#{fileType,jdbcType=VARCHAR}");
        }
        
        if (record.getFileId() != null) {
            sql.VALUES("file_id", "#{fileId,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }
}