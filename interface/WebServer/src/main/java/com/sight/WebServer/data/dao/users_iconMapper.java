package com.sight.WebServer.data.dao;

import com.sight.WebServer.data.model.users_icon;
import com.sight.WebServer.data.model.users_iconExample;
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

public interface users_iconMapper {
    @SelectProvider(type=users_iconSqlProvider.class, method="countByExample")
    long countByExample(users_iconExample example);

    @DeleteProvider(type=users_iconSqlProvider.class, method="deleteByExample")
    int deleteByExample(users_iconExample example);

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

    @SelectProvider(type=users_iconSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="icon_id", property="iconId", jdbcType=JdbcType.VARCHAR)
    })
    List<users_icon> selectByExample(users_iconExample example);

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

    @UpdateProvider(type=users_iconSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") users_icon record, @Param("example") users_iconExample example);

    @UpdateProvider(type=users_iconSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") users_icon record, @Param("example") users_iconExample example);

    @UpdateProvider(type=users_iconSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(users_icon record);

    @Update({
        "update users_icon",
        "set icon_id = #{iconId,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(users_icon record);
}