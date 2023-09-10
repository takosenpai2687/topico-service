package usyd.elec5619.topicoservice.model;

import lombok.*;

import java.util.Date;

@Data
public class Comment {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_comment.id
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_comment.post_id
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    private Integer postId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_comment.author_id
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    private Integer authorId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_comment.reply_to_comment_id
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    private Integer replyToCommentId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_comment.reply_to_user_id
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    private Integer replyToUserId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_comment.reply_to_name
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    private String replyToName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_comment.ctime
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    private Date ctime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_comment.utime
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    private Date utime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_comment.deleted
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    private Byte deleted;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_comment.content
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    private String content;

}