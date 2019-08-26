package com.sight.WebServer.data.dao;

import com.sight.WebServer.data.model.forms;
import com.sight.WebServer.data.model.formsExample;
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

public interface formsMapper {
    @SelectProvider(type=formsSqlProvider.class, method="countByExample")
    long countByExample(formsExample example);

    @DeleteProvider(type=formsSqlProvider.class, method="deleteByExample")
    int deleteByExample(formsExample example);

    @Delete({
        "delete from forms",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into forms (id, soc, ",
        "user_id, form_data)",
        "values (#{id,jdbcType=VARCHAR}, #{soc,jdbcType=VARCHAR}, ",
        "#{userId,jdbcType=VARCHAR}, #{formData,jdbcType=VARCHAR})"
    })
    int insert(forms record);

    @InsertProvider(type=formsSqlProvider.class, method="insertSelective")
    int insertSelective(forms record);

    @SelectProvider(type=formsSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="soc", property="soc", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.VARCHAR),
        @Result(column="form_data", property="formData", jdbcType=JdbcType.VARCHAR)
    })
    List<forms> selectByExample(formsExample example);

    @Select({
        "select",
        "id, soc, user_id, form_data",
        "from forms",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="soc", property="soc", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.VARCHAR),
        @Result(column="form_data", property="formData", jdbcType=JdbcType.VARCHAR)
    })
    forms selectByPrimaryKey(String id);

    @UpdateProvider(type=formsSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") forms record, @Param("example") formsExample example);

    @UpdateProvider(type=formsSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") forms record, @Param("example") formsExample example);

    @UpdateProvider(type=formsSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(forms record);

    @Update({
        "update forms",
        "set soc = #{soc,jdbcType=VARCHAR},",
          "user_id = #{userId,jdbcType=VARCHAR},",
          "form_data = #{formData,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(forms record);
}