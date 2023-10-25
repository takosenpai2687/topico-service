package usyd.elec5619.topicoservice.mapper;

import org.apache.ibatis.annotations.*;
import usyd.elec5619.topicoservice.dto.community.UpdateCommunityDto;
import usyd.elec5619.topicoservice.model.Community;
import usyd.elec5619.topicoservice.model.UserCommunity;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CommunityMapper {

    @Select("SELECT * FROM t_community WHERE id = #{communityId}")
    Community getCommunityById(Long communityId);

    @Select("SELECT * FROM t_community WHERE id IN (SELECT community_id FROM t_user_community WHERE user_id = #{userId})")
    List<Community> getCommunitiesFollowedByUser(Long userId);

    @Select("SELECT * FROM t_community WHERE id NOT IN (SELECT community_id FROM t_user_community WHERE user_id = #{userId}) ORDER BY followers, RAND() LIMIT #{limit}")
    List<Community> getCommunitiesRecommendedToUser(Long userId, Integer limit);

    @Select("SELECT * FROM t_community WHERE id IN (SELECT community_id FROM t_post WHERE id = #{postId})")
    Community getCommunityByPostId(Long postId);

    @Select("SELECT * FROM t_community WHERE LOWER(`name`) LIKE LOWER(CONCAT('%', #{keyword}, '%')) OR LOWER(`tags`) LIKE LOWER(CONCAT('%', #{keyword}, '%')) LIMIT #{limit}")
    List<Community> searchByKeyword(String keyword, Integer limit);


    @Select("SELECT * FROM t_user_community WHERE user_id = #{userId} AND community_id = #{communityId}")
    UserCommunity getUserCommunity(Long userId, Long communityId);

    @Select("SELECT EXISTS(SELECT * FROM t_user_community WHERE user_id = #{userId} AND community_id = #{communityId})")
    Boolean isUserFollowingCommunity(Long userId, Long communityId);

    @Insert("INSERT INTO t_user_community (user_id, community_id) VALUES (#{userId}, #{communityId})")
    void insertToUserCommunity(Long userId, Long communityId);

    @Update("UPDATE t_community SET followers = followers + 1 WHERE id = #{communityId}")
    void incrementCommunityFollowers(Long communityId);

    @Delete("DELETE FROM t_user_community WHERE user_id = #{userId} AND community_id = #{communityId}")
    void deleteFromUserCommunity(Long userId, Long communityId);

    @Update("UPDATE t_community SET followers = followers - 1 WHERE id = #{communityId}")
    void decrementCommunityFollowers(Long communityId);

    @Select("SELECT * FROM t_community")
    List<Community> getAllCommunities();

    @Select("SELECT * FROM t_community ORDER BY followers DESC LIMIT 10")
    List<Community> getTopCommunities();

    @Insert("INSERT INTO t_community(name, description, followers, avatar, banner) " +
            "VALUES (#{name}, #{description}, #{followers}, #{avatar}, #{banner})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void insertOne(Community community);

    @Update("UPDATE t_community SET name = #{updateDto.name}, description = #{updateDto.description}," +
            " avatar = #{updateDto.avatar}, banner = #{updateDto.banner} WHERE id = #{communityId}")
    void updateCommunity(@Param("communityId") Long id, @Param("updateDto") UpdateCommunityDto updateCommunityDto);

    @Delete("DELETE FROM t_user_community WHERE community_id = #{communityId}")
    void unfollowCommunityForAllUsers(Long communityId);


    @Delete("DELETE FROM t_community WHERE id = #{communityId}")
    void deleteOne(Long communityId);

    @Select("SELECT * FROM t_community WHERE name = #{communityName}")
    Optional<Community> getCommunityByName(String communityName);

    @Select("SELECT `rank` FROM (SELECT id, DENSE_RANK() OVER (ORDER BY followers DESC) AS `rank` FROM t_community) ranked_communities WHERE id = #{communityId}")
    Integer getRankBySize(Long communityId);

    @Select("SELECT `level` from t_user_community where user_id = #{userId} and community_id = #{communityId}")
    Optional<Integer> getMyLevel(Long userId, Long communityId);
}