package com.sight.WebServer.data.dao;

import com.sight.WebServer.data.model.record_master;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface record_masterMapper {
    @Delete({
        "delete from record_master",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into record_master (id, type, ",
        "location)",
        "values (#{id,jdbcType=VARCHAR}, #{type,jdbcType=VARCHAR}, ",
        "#{location,jdbcType=VARCHAR})"
    })
    int insert(record_master record);

    @InsertProvider(type=record_masterSqlProvider.class, method="insertSelective")
    int insertSelective(record_master record);

    @Select({
        "select",
        "id, type, location",
        "from record_master",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
        @Result(column="location", property="location", jdbcType=JdbcType.VARCHAR)
    })
    record_master selectByPrimaryKey(String id);

    @UpdateProvider(type=record_masterSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(record_master record);

    @Update({
        "update record_master",
        "set type = #{type,jdbcType=VARCHAR},",
          "location = #{location,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(record_master record);
}