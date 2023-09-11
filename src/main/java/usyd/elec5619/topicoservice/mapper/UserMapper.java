package usyd.elec5619.topicoservice.mapper;

import org.apache.ibatis.annotations.*;
import usyd.elec5619.topicoservice.dto.user.UpdateUserDto;
import usyd.elec5619.topicoservice.model.User;

@Mapper
public interface UserMapper {
    @Insert("INSERT INTO t_user (email, nick_name, password) " +
            "VALUES (#{email}, #{nickName}, #{password})")
    void insertOne(User user);

    @Select("SELECT * FROM t_user WHERE id = #{id}")
    User getByEmail(String email);

    @Select("SELECT * FROM t_user WHERE nick_name = #{nickName}")
    User getByNickName(String nickName);

    @Delete("DELETE FROM t_user WHERE id = #{id}")
    void deleteById(Long id);

    @Delete("DELETE FROM t_user WHERE email = #{email}")
    void deleteByEmail(String email);

    @Update({
            "<script>",
            "UPDATE t_user",
            "<set>",
            "<if test='dto.nickName != null'>nickName=#{dto.nickName},</if>",
            "<if test='dto.password != null'>password=#{dto.password},</if>",
            "<if test='dto.gender != null'>gender=#{dto.gender},</if>",
            "<if test='dto.location != null'>location=#{dto.location},</if>",
            "<if test='dto.avatar != null'>avatar=#{dto.avatar},</if>",
            "<if test='dto.description != null'>description=#{dto.description}</if>",
            "</set>",
            "WHERE id=#{id}",
            "</script>"
    })
    User updateUser(@Param("id") Long id, @Param("dto") UpdateUserDto updateUserDTO);

    @Insert("INSERT INTO t_user (email, nick_name, password) " +
            "VALUES (#{email}, #{nickName}, #{password})")
    void insert(User user);

}