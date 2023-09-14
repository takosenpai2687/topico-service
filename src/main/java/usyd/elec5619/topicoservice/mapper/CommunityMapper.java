package usyd.elec5619.topicoservice.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import usyd.elec5619.topicoservice.model.Community;
import usyd.elec5619.topicoservice.model.UserCommunity;

import java.util.List;

@Mapper
public interface CommunityMapper {

    @Select("SELECT * FROM t_community WHERE id = #{communityId}")
    Community getCommunityById(Long communityId);

    @Select("SELECT * FROM t_community WHERE id IN (SELECT community_id FROM t_user_community WHERE user_id = #{userId})")
    List<Community> getCommunitiesFollowedByUser(Long userId);

    @Select("SELECT * FROM t_community WHERE id NOT IN (SELECT community_id FROM t_user_community WHERE user_id = #{userId}) ORDER BY RAND() LIMIT #{limit}")
    List<Community> getCommunitiesRecommendedToUser(Long userId, Integer limit);


    @Select("SELECT * FROM t_community WHERE id IN (SELECT community_id FROM t_post WHERE id = #{postId})")
    Community getCommunityByPostId(Long postId);

    @Select("SELECT * FROM t_community WHERE name LIKE CONCAT('%', #{keyword}, '%') LIMIT #{limit}")
    List<Community> searchByName(String keyword, Integer limit);

    @Select("SELECT * FROM t_user_community WHERE user_id = #{userId} AND community_id = #{communityId}")
    UserCommunity getUserCommunity(Long userId, Long communityId);

    @Select("SELECT EXISTS(SELECT * FROM t_user_community WHERE user_id = #{userId} AND community_id = #{communityId})")
    Boolean isUserFollowingCommunity(Long userId, Long communityId);

    @Insert({
            "<script>",
            "INSERT INTO t_user_community (user_id, community_id) VALUES (#{userId}, #{communityId});",
            "UPDATE t_community SET followers = followers + 1 WHERE id = #{communityId};",
            "</script>"
    })
    void follow(Long userId, Long communityId);

    @Delete({
            "<script>",
            "DELETE FROM t_user_community WHERE user_id = #{userId} AND community_id = #{communityId};",
            "UPDATE t_community SET followers = followers - 1 WHERE id = #{communityId};",
            "</script>"
    })
    void unfollow(Long userId, Long communityId);
}