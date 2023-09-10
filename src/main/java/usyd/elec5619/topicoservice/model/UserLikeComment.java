package usyd.elec5619.topicoservice.model;

import java.util.Date;

public class UserLikeComment {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_like_comment.id
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_like_comment.user_id
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    private Integer userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_like_comment.author_id
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    private Integer authorId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_like_comment.comment_id
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    private Integer commentId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_like_comment.like
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    private Byte like;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_like_comment.ctime
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    private Date ctime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_like_comment.utime
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    private Date utime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_like_comment.id
     *
     * @return the value of t_user_like_comment.id
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_like_comment.id
     *
     * @param id the value for t_user_like_comment.id
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_like_comment.user_id
     *
     * @return the value of t_user_like_comment.user_id
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_like_comment.user_id
     *
     * @param userId the value for t_user_like_comment.user_id
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_like_comment.author_id
     *
     * @return the value of t_user_like_comment.author_id
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    public Integer getAuthorId() {
        return authorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_like_comment.author_id
     *
     * @param authorId the value for t_user_like_comment.author_id
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_like_comment.comment_id
     *
     * @return the value of t_user_like_comment.comment_id
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    public Integer getCommentId() {
        return commentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_like_comment.comment_id
     *
     * @param commentId the value for t_user_like_comment.comment_id
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_like_comment.like
     *
     * @return the value of t_user_like_comment.like
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    public Byte getLike() {
        return like;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_like_comment.like
     *
     * @param like the value for t_user_like_comment.like
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    public void setLike(Byte like) {
        this.like = like;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_like_comment.ctime
     *
     * @return the value of t_user_like_comment.ctime
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    public Date getCtime() {
        return ctime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_like_comment.ctime
     *
     * @param ctime the value for t_user_like_comment.ctime
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_like_comment.utime
     *
     * @return the value of t_user_like_comment.utime
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    public Date getUtime() {
        return utime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_like_comment.utime
     *
     * @param utime the value for t_user_like_comment.utime
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    public void setUtime(Date utime) {
        this.utime = utime;
    }
}