package com.sight.WebServer.data.dao;

import com.sight.WebServer.data.model.users_icon;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface users_iconMapper {
    @Delete({
        "delete from users_icon",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into users_icon (id, icon_id)",
        "values (#{id,jdbcType=VARCHAR}, #{iconId,jdbcType=VARCHAR})"
    })
    int insert(users_icon record);

    @InsertProvider(type=users_iconSqlProvider.class, method="insertSelective")
    int insertSelective(users_icon record);

    @Select({
        "select",
        "id, icon_id",
        "from users_icon",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="icon_id", property="iconId", jdbcType=JdbcType.VARCHAR)
    })
    users_icon selectByPrimaryKey(String id);

    @UpdateProvider(type=users_iconSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(users_icon record);

    @Update({
        "update users_icon",
        "set icon_id = #{iconId,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(users_icon record);
}