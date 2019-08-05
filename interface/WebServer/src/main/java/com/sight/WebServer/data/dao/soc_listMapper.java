package com.sight.WebServer.data.dao;

import com.sight.WebServer.data.model.soc_list;
import com.sight.WebServer.data.model.soc_listExample;
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

public interface soc_listMapper {
    @SelectProvider(type=soc_listSqlProvider.class, method="countByExample")
    long countByExample(soc_listExample example);

    @DeleteProvider(type=soc_listSqlProvider.class, method="deleteByExample")
    int deleteByExample(soc_listExample example);

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

    @SelectProvider(type=soc_listSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="soc", property="soc", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR)
    })
    List<soc_list> selectByExample(soc_listExample example);

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

    @UpdateProvider(type=soc_listSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") soc_list record, @Param("example") soc_listExample example);

    @UpdateProvider(type=soc_listSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") soc_list record, @Param("example") soc_listExample example);

    @UpdateProvider(type=soc_listSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(soc_list record);

    @Update({
        "update soc_list",
        "set name = #{name,jdbcType=VARCHAR}",
        "where soc = #{soc,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(soc_list record);
}