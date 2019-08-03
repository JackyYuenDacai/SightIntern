package com.sight.WebServer.data.dao;

import com.sight.WebServer.data.model.users;
import com.sight.WebServer.data.model.usersExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

@Mapper
public interface usersMapper {
    @SelectProvider(type=usersSqlProvider.class, method="countByExample")
    long countByExample(usersExample example);

    @DeleteProvider(type=usersSqlProvider.class, method="deleteByExample")
    int deleteByExample(usersExample example);

    @Delete({
        "delete from users",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into users (id, name, ",
        "role, privilege, ",
        "extra, pwd, soc, ",
        "parent_id)",
        "values (#{id,jdbcType=VARCHAR}, #{name,jdbcType=VARCHAR}, ",
        "#{role,jdbcType=VARCHAR}, #{privilege,jdbcType=INTEGER}, ",
        "#{extra,jdbcType=VARCHAR}, #{pwd,jdbcType=VARCHAR}, #{soc,jdbcType=VARCHAR}, ",
        "#{parentId,jdbcType=VARCHAR})"
    })
    int insert(users record);

    @InsertProvider(type=usersSqlProvider.class, method="insertSelective")
    int insertSelective(users record);

    @SelectProvider(type=usersSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="role", property="role", jdbcType=JdbcType.VARCHAR),
        @Result(column="privilege", property="privilege", jdbcType=JdbcType.INTEGER),
        @Result(column="extra", property="extra", jdbcType=JdbcType.VARCHAR),
        @Result(column="pwd", property="pwd", jdbcType=JdbcType.VARCHAR),
        @Result(column="soc", property="soc", jdbcType=JdbcType.VARCHAR),
        @Result(column="parent_id", property="parentId", jdbcType=JdbcType.VARCHAR)
    })
    List<users> selectByExample(usersExample example);

    @Select({
        "select",
        "id, name, role, privilege, extra, pwd, soc, parent_id",
        "from users",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="role", property="role", jdbcType=JdbcType.VARCHAR),
        @Result(column="privilege", property="privilege", jdbcType=JdbcType.INTEGER),
        @Result(column="extra", property="extra", jdbcType=JdbcType.VARCHAR),
        @Result(column="pwd", property="pwd", jdbcType=JdbcType.VARCHAR),
        @Result(column="soc", property="soc", jdbcType=JdbcType.VARCHAR),
        @Result(column="parent_id", property="parentId", jdbcType=JdbcType.VARCHAR)
    })
    users selectByPrimaryKey(String id);

    @UpdateProvider(type=usersSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") users record, @Param("example") usersExample example);

    @UpdateProvider(type=usersSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") users record, @Param("example") usersExample example);

    @UpdateProvider(type=usersSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(users record);

    @Update({
        "update users",
        "set name = #{name,jdbcType=VARCHAR},",
          "role = #{role,jdbcType=VARCHAR},",
          "privilege = #{privilege,jdbcType=INTEGER},",
          "extra = #{extra,jdbcType=VARCHAR},",
          "pwd = #{pwd,jdbcType=VARCHAR},",
          "soc = #{soc,jdbcType=VARCHAR},",
          "parent_id = #{parentId,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(users record);
}