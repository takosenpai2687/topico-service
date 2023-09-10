package usyd.elec5619.topicoservice.model;

import lombok.*;

import java.util.Date;

@Data
public class User {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.id
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.email
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    private String email;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.nick_name
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    private String nickName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.password
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    private String password;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.gender
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    private Byte gender;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.location
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    private String location;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.avatar
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    private String avatar;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.ctime
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    private Date ctime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.utime
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    private Date utime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.deleted
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    private Byte deleted;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.description
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    private String description;

}