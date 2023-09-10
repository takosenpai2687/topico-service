package usyd.elec5619.topicoservice.model;

import java.util.Date;

public class Notification {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_notification.id
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_notification.type
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    private Byte type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_notification.sender_id
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    private Integer senderId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_notification.receiver_id
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    private Integer receiverId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_notification.unread
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    private Byte unread;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_notification.ctime
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    private Date ctime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_notification.utime
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    private Date utime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_notification.content
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    private String content;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_notification.id
     *
     * @return the value of t_notification.id
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_notification.id
     *
     * @param id the value for t_notification.id
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_notification.type
     *
     * @return the value of t_notification.type
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    public Byte getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_notification.type
     *
     * @param type the value for t_notification.type
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    public void setType(Byte type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_notification.sender_id
     *
     * @return the value of t_notification.sender_id
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    public Integer getSenderId() {
        return senderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_notification.sender_id
     *
     * @param senderId the value for t_notification.sender_id
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_notification.receiver_id
     *
     * @return the value of t_notification.receiver_id
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    public Integer getReceiverId() {
        return receiverId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_notification.receiver_id
     *
     * @param receiverId the value for t_notification.receiver_id
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_notification.unread
     *
     * @return the value of t_notification.unread
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    public Byte getUnread() {
        return unread;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_notification.unread
     *
     * @param unread the value for t_notification.unread
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    public void setUnread(Byte unread) {
        this.unread = unread;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_notification.ctime
     *
     * @return the value of t_notification.ctime
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    public Date getCtime() {
        return ctime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_notification.ctime
     *
     * @param ctime the value for t_notification.ctime
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_notification.utime
     *
     * @return the value of t_notification.utime
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    public Date getUtime() {
        return utime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_notification.utime
     *
     * @param utime the value for t_notification.utime
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    public void setUtime(Date utime) {
        this.utime = utime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_notification.content
     *
     * @return the value of t_notification.content
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_notification.content
     *
     * @param content the value for t_notification.content
     *
     * @mbg.generated Mon Sep 11 03:55:35 AEST 2023
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}