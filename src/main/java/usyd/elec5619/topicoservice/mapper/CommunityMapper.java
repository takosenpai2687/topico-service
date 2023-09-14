package usyd.elec5619.topicoservice.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import usyd.elec5619.topicoservice.model.Community;

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
}