package com.sight.WebServer.data.dao;

import com.sight.WebServer.data.model.users_files;
import com.sight.WebServer.data.model.users_filesExample;
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
public interface users_filesMapper {
    @SelectProvider(type=users_filesSqlProvider.class, method="countByExample")
    long countByExample(users_filesExample example);

    @DeleteProvider(type=users_filesSqlProvider.class, method="deleteByExample")
    int deleteByExample(users_filesExample example);

    @Delete({
        "delete from users_files",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into users_files (id, pid, ",
        "file_type, file_id)",
        "values (#{id,jdbcType=VARCHAR}, #{pid,jdbcType=VARCHAR}, ",
        "#{fileType,jdbcType=VARCHAR}, #{fileId,jdbcType=VARCHAR})"
    })
    int insert(users_files record);

    @InsertProvider(type=users_filesSqlProvider.class, method="insertSelective")
    int insertSelective(users_files record);

    @SelectProvider(type=users_filesSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="pid", property="pid", jdbcType=JdbcType.VARCHAR),
        @Result(column="file_type", property="fileType", jdbcType=JdbcType.VARCHAR),
        @Result(column="file_id", property="fileId", jdbcType=JdbcType.VARCHAR)
    })
    List<users_files> selectByExample(users_filesExample example);

    @Select({
        "select",
        "id, pid, file_type, file_id",
        "from users_files",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="pid", property="pid", jdbcType=JdbcType.VARCHAR),
        @Result(column="file_type", property="fileType", jdbcType=JdbcType.VARCHAR),
        @Result(column="file_id", property="fileId", jdbcType=JdbcType.VARCHAR)
    })
    users_files selectByPrimaryKey(String id);

    @UpdateProvider(type=users_filesSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") users_files record, @Param("example") users_filesExample example);

    @UpdateProvider(type=users_filesSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") users_files record, @Param("example") users_filesExample example);

    @UpdateProvider(type=users_filesSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(users_files record);

    @Update({
        "update users_files",
        "set pid = #{pid,jdbcType=VARCHAR},",
          "file_type = #{fileType,jdbcType=VARCHAR},",
          "file_id = #{fileId,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(users_files record);
}