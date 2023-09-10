package usyd.elec5619.topicoservice.model;

import java.util.Date;

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

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.id
     *
     * @return the value of t_user.id
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.id
     *
     * @param id the value for t_user.id
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.email
     *
     * @return the value of t_user.email
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.email
     *
     * @param email the value for t_user.email
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.nick_name
     *
     * @return the value of t_user.nick_name
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.nick_name
     *
     * @param nickName the value for t_user.nick_name
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.password
     *
     * @return the value of t_user.password
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.password
     *
     * @param password the value for t_user.password
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.gender
     *
     * @return the value of t_user.gender
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    public Byte getGender() {
        return gender;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.gender
     *
     * @param gender the value for t_user.gender
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    public void setGender(Byte gender) {
        this.gender = gender;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.location
     *
     * @return the value of t_user.location
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    public String getLocation() {
        return location;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.location
     *
     * @param location the value for t_user.location
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.avatar
     *
     * @return the value of t_user.avatar
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.avatar
     *
     * @param avatar the value for t_user.avatar
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.ctime
     *
     * @return the value of t_user.ctime
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    public Date getCtime() {
        return ctime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.ctime
     *
     * @param ctime the value for t_user.ctime
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.utime
     *
     * @return the value of t_user.utime
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    public Date getUtime() {
        return utime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.utime
     *
     * @param utime the value for t_user.utime
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    public void setUtime(Date utime) {
        this.utime = utime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.deleted
     *
     * @return the value of t_user.deleted
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    public Byte getDeleted() {
        return deleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.deleted
     *
     * @param deleted the value for t_user.deleted
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    public void setDeleted(Byte deleted) {
        this.deleted = deleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.description
     *
     * @return the value of t_user.description
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.description
     *
     * @param description the value for t_user.description
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}