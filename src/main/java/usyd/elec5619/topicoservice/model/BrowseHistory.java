package usyd.elec5619.topicoservice.model;

import java.util.Date;

public class BrowseHistory {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_browse_history.id
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_browse_history.user_id
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    private Integer userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_browse_history.post_id
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    private Integer postId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_browse_history.ctime
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    private Date ctime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_browse_history.utime
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    private Date utime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_browse_history.id
     *
     * @return the value of t_browse_history.id
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_browse_history.id
     *
     * @param id the value for t_browse_history.id
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_browse_history.user_id
     *
     * @return the value of t_browse_history.user_id
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_browse_history.user_id
     *
     * @param userId the value for t_browse_history.user_id
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_browse_history.post_id
     *
     * @return the value of t_browse_history.post_id
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    public Integer getPostId() {
        return postId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_browse_history.post_id
     *
     * @param postId the value for t_browse_history.post_id
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_browse_history.ctime
     *
     * @return the value of t_browse_history.ctime
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    public Date getCtime() {
        return ctime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_browse_history.ctime
     *
     * @param ctime the value for t_browse_history.ctime
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_browse_history.utime
     *
     * @return the value of t_browse_history.utime
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    public Date getUtime() {
        return utime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_browse_history.utime
     *
     * @param utime the value for t_browse_history.utime
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    public void setUtime(Date utime) {
        this.utime = utime;
    }
}