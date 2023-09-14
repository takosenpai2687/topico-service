package usyd.elec5619.topicoservice.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import usyd.elec5619.topicoservice.model.UserCommunity;

import java.util.List;

@Mapper
public interface CheckinMapper {
    @Select("SELECT checkin FROM t_user_community WHERE user_id = #{userId} AND community_id = #{communityId}")
    Integer getCheckinBitMap(Long userId, Long communityId);

    @Update("UPDATE t_user_community SET checkin = #{checkinBitMap} WHERE user_id = #{userId} AND community_id = #{communityId}")
    void updateCheckinBitMap(Long userId, Long communityId, Integer checkinBitMap);

    @Select("SELECT * FROM t_user_community WHERE user_id = #{userId}")
    List<UserCommunity> getUserCommunities(Long userId);

    @Update({
            "<script>",
            "UPDATE t_user_community",
            "SET checkin = CASE",
            "<foreach collection='userCommunities' item='userCommunity' index='index' separator=' '>",
            "WHEN user_id = #{userCommunity.userId} AND community_id = #{userCommunity.communityId} THEN #{userCommunity.checkin}",
            "</foreach>",
            "ELSE checkin END",
            "WHERE (user_id, community_id) IN",
            "<foreach collection='userCommunities' item='userCommunity' index='index' open='(' separator=',' close=')'>",
            "(#{userCommunity.userId}, #{userCommunity.communityId})",
            "</foreach>",
            "</script>"
    })
    void updateCheckinBitMaps(List<UserCommunity> userCommunities);
}