package com.sight.WebServer.data.dao;

import com.sight.WebServer.data.model.users_token;
import com.sight.WebServer.data.model.users_tokenExample;
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

public interface users_tokenMapper {
    @SelectProvider(type=users_tokenSqlProvider.class, method="countByExample")
    long countByExample(users_tokenExample example);

    @DeleteProvider(type=users_tokenSqlProvider.class, method="deleteByExample")
    int deleteByExample(users_tokenExample example);

    @Delete({
        "delete from users_token",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into users_token (id, token, ",
        "expire)",
        "values (#{id,jdbcType=VARCHAR}, #{token,jdbcType=VARCHAR}, ",
        "#{expire,jdbcType=TIMESTAMP})"
    })
    int insert(users_token record);

    @InsertProvider(type=users_tokenSqlProvider.class, method="insertSelective")
    int insertSelective(users_token record);

    @SelectProvider(type=users_tokenSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="token", property="token", jdbcType=JdbcType.VARCHAR),
        @Result(column="expire", property="expire", jdbcType=JdbcType.TIMESTAMP)
    })
    List<users_token> selectByExample(users_tokenExample example);

    @Select({
        "select",
        "id, token, expire",
        "from users_token",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="token", property="token", jdbcType=JdbcType.VARCHAR),
        @Result(column="expire", property="expire", jdbcType=JdbcType.TIMESTAMP)
    })
    users_token selectByPrimaryKey(String id);

    @UpdateProvider(type=users_tokenSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") users_token record, @Param("example") users_tokenExample example);

    @UpdateProvider(type=users_tokenSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") users_token record, @Param("example") users_tokenExample example);

    @UpdateProvider(type=users_tokenSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(users_token record);

    @Update({
        "update users_token",
        "set token = #{token,jdbcType=VARCHAR},",
          "expire = #{expire,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(users_token record);
}