package usyd.elec5619.topicoservice.mapper;

import org.apache.ibatis.annotations.*;
import usyd.elec5619.topicoservice.dto.user.UpdateUserDto;
import usyd.elec5619.topicoservice.model.Community;
import usyd.elec5619.topicoservice.model.Post;
import usyd.elec5619.topicoservice.model.User;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserMapper {

    @Select("SELECT id FROM t_user WHERE email = #{email}")
    Optional<Long> emailToId(String email);

    @Insert("INSERT INTO t_user (email, nick_name, password, gender, role) " + "VALUES (#{email}, #{nickName}, #{password}, #{gender}, #{role})")
    void insertOne(User user);

    @Select("SELECT * FROM t_user WHERE email = #{email}")
    Optional<User> getByEmail(String email);

    @Select("SELECT * FROM t_user WHERE nick_name = #{nickName}")
    User getByNickName(String nickName);

    @Delete("DELETE FROM t_user WHERE id = #{id}")
    void deleteById(Long id);

    @Update({"<script>", "UPDATE t_user", "<set>", "<if test='dto.nickName != null'>nickName=#{dto.nickName},</if>", "<if test='dto.gender != null'>gender=#{dto.gender},</if>", "<if test='dto.location != null'>location=#{dto.location},</if>", "<if test='dto.avatar != null'>avatar=#{dto.avatar},</if>", "<if test='dto.description != null'>description=#{dto.description}</if>", "</set>", "WHERE id=#{id}", "</script>"})
    User updateUser(@Param("id") Long id, @Param("dto") UpdateUserDto updateUserDTO);

    @Update("UPDATE t_user SET password = #{password} WHERE id = #{id}")
    void updateUserPassword(@Param("id") Long id, @Param("password") String password);

    @Select("SELECT * FROM t_user WHERE id = #{id}")
    Optional<User> getUserById(Long id);

    @Select("SELECT * FROM t_user")
    List<User> getAllUsers();

    @Update("UPDATE t_user SET password = #{password} WHERE id = #{userId}")
    void updatePassword(Long userId, String password);
}