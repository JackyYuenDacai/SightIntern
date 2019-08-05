package com.sight.WebServer.data.dao;

import com.sight.WebServer.data.model.record_master;
import com.sight.WebServer.data.model.record_masterExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface record_masterMapper {
    @SelectProvider(type=record_masterSqlProvider.class, method="countByExample")
    long countByExample(record_masterExample example);

    @DeleteProvider(type=record_masterSqlProvider.class, method="deleteByExample")
    int deleteByExample(record_masterExample example);

    @Delete({
        "delete from record_master",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into record_master (id, type, ",
        "location, record_in, ",
        "record_out, data, ",
        "token)",
        "values (#{id,jdbcType=VARCHAR}, #{type,jdbcType=VARCHAR}, ",
        "#{location,jdbcType=VARCHAR}, #{recordIn,jdbcType=TIMESTAMP}, ",
        "#{recordOut,jdbcType=TIMESTAMP}, #{data,jdbcType=VARCHAR}, ",
        "#{token,jdbcType=VARCHAR})"
    })
    int insert(record_master record);

    @InsertProvider(type=record_masterSqlProvider.class, method="insertSelective")
    int insertSelective(record_master record);

    @SelectProvider(type=record_masterSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
        @Result(column="location", property="location", jdbcType=JdbcType.VARCHAR),
        @Result(column="record_in", property="recordIn", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="record_out", property="recordOut", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="data", property="data", jdbcType=JdbcType.VARCHAR),
        @Result(column="token", property="token", jdbcType=JdbcType.VARCHAR)
    })
    List<record_master> selectByExample(record_masterExample example);

    @Select({
        "select",
        "id, type, location, record_in, record_out, data, token",
        "from record_master",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
        @Result(column="location", property="location", jdbcType=JdbcType.VARCHAR),
        @Result(column="record_in", property="recordIn", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="record_out", property="recordOut", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="data", property="data", jdbcType=JdbcType.VARCHAR),
        @Result(column="token", property="token", jdbcType=JdbcType.VARCHAR)
    })
    record_master selectByPrimaryKey(String id);

    @UpdateProvider(type=record_masterSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") record_master record, @Param("example") record_masterExample example);

    @UpdateProvider(type=record_masterSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") record_master record, @Param("example") record_masterExample example);

    @UpdateProvider(type=record_masterSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(record_master record);

    @Update({
        "update record_master",
        "set type = #{type,jdbcType=VARCHAR},",
          "location = #{location,jdbcType=VARCHAR},",
          "record_in = #{recordIn,jdbcType=TIMESTAMP},",
          "record_out = #{recordOut,jdbcType=TIMESTAMP},",
          "data = #{data,jdbcType=VARCHAR},",
          "token = #{token,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(record_master record);
}