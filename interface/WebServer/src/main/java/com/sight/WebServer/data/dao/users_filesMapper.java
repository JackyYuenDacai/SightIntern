package com.sight.WebServer.data.dao;

import com.sight.WebServer.data.model.users_files;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;

public interface users_filesMapper {
    @Insert({
        "insert into users_files (id, pid, ",
        "file_type, file_id)",
        "values (#{id,jdbcType=VARCHAR}, #{pid,jdbcType=VARCHAR}, ",
        "#{fileType,jdbcType=VARCHAR}, #{fileId,jdbcType=VARCHAR})"
    })
    int insert(users_files record);

    @InsertProvider(type=users_filesSqlProvider.class, method="insertSelective")
    int insertSelective(users_files record);
}