package com.sight.WebServer.data.dao;

import com.sight.WebServer.data.model.users_tag;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;

public interface users_tagMapper {
    @Insert({
        "insert into users_tag (id, tag_type, ",
        "tag_id)",
        "values (#{id,jdbcType=VARCHAR}, #{tagType,jdbcType=VARCHAR}, ",
        "#{tagId,jdbcType=VARCHAR})"
    })
    int insert(users_tag record);

    @InsertProvider(type=users_tagSqlProvider.class, method="insertSelective")
    int insertSelective(users_tag record);
}