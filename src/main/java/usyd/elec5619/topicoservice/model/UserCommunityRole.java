package usyd.elec5619.topicoservice.model;

import lombok.*;

import java.util.Date;

@Data
public class UserCommunityRole {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_community_role.id
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_community_role.user_id
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    private Integer userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_community_role.community_id
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    private Integer communityId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_community_role.role_id
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    private Integer roleId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_community_role.level
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    private Integer level;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_community_role.exp
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    private Integer exp;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_community_role.ctime
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    private Date ctime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_community_role.utime
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    private Date utime;

}