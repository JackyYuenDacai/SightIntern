package com.sight.WebServer.data.dao;

import com.sight.WebServer.data.model.users_tag;
import com.sight.WebServer.data.model.users_tagExample;
import java.util.List;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface users_tagMapper {
    @SelectProvider(type=users_tagSqlProvider.class, method="countByExample")
    long countByExample(users_tagExample example);

    @DeleteProvider(type=users_tagSqlProvider.class, method="deleteByExample")
    int deleteByExample(users_tagExample example);

    @Insert({
        "insert into users_tag (id, tag_type, ",
        "tag_id)",
        "values (#{id,jdbcType=VARCHAR}, #{tagType,jdbcType=VARCHAR}, ",
        "#{tagId,jdbcType=VARCHAR})"
    })
    int insert(users_tag record);

    @InsertProvider(type=users_tagSqlProvider.class, method="insertSelective")
    int insertSelective(users_tag record);

    @SelectProvider(type=users_tagSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR),
        @Result(column="tag_type", property="tagType", jdbcType=JdbcType.VARCHAR),
        @Result(column="tag_id", property="tagId", jdbcType=JdbcType.VARCHAR)
    })
    List<users_tag> selectByExample(users_tagExample example);

    @UpdateProvider(type=users_tagSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") users_tag record, @Param("example") users_tagExample example);

    @UpdateProvider(type=users_tagSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") users_tag record, @Param("example") users_tagExample example);
}