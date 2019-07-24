package com.sight.WebServer.data.dao;

import com.sight.WebServer.data.model.record_child;
import com.sight.WebServer.data.model.record_childExample;
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

public interface record_childMapper {
    @SelectProvider(type=record_childSqlProvider.class, method="countByExample")
    long countByExample(record_childExample example);

    @DeleteProvider(type=record_childSqlProvider.class, method="deleteByExample")
    int deleteByExample(record_childExample example);

    @Delete({
        "delete from record_child",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into record_child (id, parent_id, ",
        "student_id, record_time, ",
        "data, status)",
        "values (#{id,jdbcType=VARCHAR}, #{parentId,jdbcType=VARCHAR}, ",
        "#{studentId,jdbcType=VARCHAR}, #{recordTime,jdbcType=TIMESTAMP}, ",
        "#{data,jdbcType=VARCHAR}, #{status,jdbcType=INTEGER})"
    })
    int insert(record_child record);

    @InsertProvider(type=record_childSqlProvider.class, method="insertSelective")
    int insertSelective(record_child record);

    @SelectProvider(type=record_childSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="parent_id", property="parentId", jdbcType=JdbcType.VARCHAR),
        @Result(column="student_id", property="studentId", jdbcType=JdbcType.VARCHAR),
        @Result(column="record_time", property="recordTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="data", property="data", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER)
    })
    List<record_child> selectByExample(record_childExample example);

    @Select({
        "select",
        "id, parent_id, student_id, record_time, data, status",
        "from record_child",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="parent_id", property="parentId", jdbcType=JdbcType.VARCHAR),
        @Result(column="student_id", property="studentId", jdbcType=JdbcType.VARCHAR),
        @Result(column="record_time", property="recordTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="data", property="data", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER)
    })
    record_child selectByPrimaryKey(String id);

    @UpdateProvider(type=record_childSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") record_child record, @Param("example") record_childExample example);

    @UpdateProvider(type=record_childSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") record_child record, @Param("example") record_childExample example);

    @UpdateProvider(type=record_childSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(record_child record);

    @Update({
        "update record_child",
        "set parent_id = #{parentId,jdbcType=VARCHAR},",
          "student_id = #{studentId,jdbcType=VARCHAR},",
          "record_time = #{recordTime,jdbcType=TIMESTAMP},",
          "data = #{data,jdbcType=VARCHAR},",
          "status = #{status,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(record_child record);
}