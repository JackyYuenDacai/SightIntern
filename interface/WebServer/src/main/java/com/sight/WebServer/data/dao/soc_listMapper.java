package com.sight.WebServer.data.dao;

import com.sight.WebServer.data.model.soc_list;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface soc_listMapper {
    @Delete({
        "delete from soc_list",
        "where soc = #{soc,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String soc);

    @Insert({
        "insert into soc_list (soc, name)",
        "values (#{soc,jdbcType=VARCHAR}, #{name,jdbcType=VARCHAR})"
    })
    int insert(soc_list record);

    @InsertProvider(type=soc_listSqlProvider.class, method="insertSelective")
    int insertSelective(soc_list record);

    @Select({
        "select",
        "soc, name",
        "from soc_list",
        "where soc = #{soc,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="soc", property="soc", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR)
    })
    soc_list selectByPrimaryKey(String soc);

    @UpdateProvider(type=soc_listSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(soc_list record);

    @Update({
        "update soc_list",
        "set name = #{name,jdbcType=VARCHAR}",
        "where soc = #{soc,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(soc_list record);
}