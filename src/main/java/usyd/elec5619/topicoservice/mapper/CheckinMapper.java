package usyd.elec5619.topicoservice.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import usyd.elec5619.topicoservice.model.UserCommunity;

import java.util.List;

@Mapper
public interface CheckinMapper {
    @Select("SELECT * FROM t_user_community WHERE user_id = #{userId} AND community_id = #{communityId}")
    UserCommunity getUserCommunity(Long userId, Long communityId);

    @Update("UPDATE t_user_community SET checkin = #{checkin}, exp = #{exp} WHERE user_id = #{userId} AND community_id = #{communityId}")
    void checkin(UserCommunity userCommunity);

    @Select("SELECT * FROM t_user_community WHERE user_id = #{userId}")
    List<UserCommunity> getUserCommunities(Long userId);

    @Update({
            "<script>",
            "<foreach collection='list' item='userCommunity' separator=';'>",
            "UPDATE t_user_community",
            "SET checkin = #{userCommunity.checkin}, exp = #{userCommunity.exp}",
            "WHERE user_id = #{userCommunity.userId}",
            "AND community_id = #{userCommunity.communityId}",
            "</foreach>",
            "</script>"
    })
    void checkinForAll(List<UserCommunity> userCommunities);
}