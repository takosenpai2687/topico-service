package usyd.elec5619.topicoservice.model;

public class PostTag {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_post_tag.id
     *
     * @mbg.generated Mon Sep 11 03:54:13 AEST 2023
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_post_tag.post_id
     *
     * @mbg.generated Mon Sep 11 03:54:13 AEST 2023
     */
    private Integer postId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_post_tag.tag_id
     *
     * @mbg.generated Mon Sep 11 03:54:13 AEST 2023
     */
    private Integer tagId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_post_tag.id
     *
     * @return the value of t_post_tag.id
     *
     * @mbg.generated Mon Sep 11 03:54:13 AEST 2023
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_post_tag.id
     *
     * @param id the value for t_post_tag.id
     *
     * @mbg.generated Mon Sep 11 03:54:13 AEST 2023
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_post_tag.post_id
     *
     * @return the value of t_post_tag.post_id
     *
     * @mbg.generated Mon Sep 11 03:54:13 AEST 2023
     */
    public Integer getPostId() {
        return postId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_post_tag.post_id
     *
     * @param postId the value for t_post_tag.post_id
     *
     * @mbg.generated Mon Sep 11 03:54:13 AEST 2023
     */
    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_post_tag.tag_id
     *
     * @return the value of t_post_tag.tag_id
     *
     * @mbg.generated Mon Sep 11 03:54:13 AEST 2023
     */
    public Integer getTagId() {
        return tagId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_post_tag.tag_id
     *
     * @param tagId the value for t_post_tag.tag_id
     *
     * @mbg.generated Mon Sep 11 03:54:13 AEST 2023
     */
    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }
}