package usyd.elec5619.topicoservice.model;

import java.util.Date;

public class Permission {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_permission.id
     *
     * @mbg.generated Mon Sep 11 03:54:13 AEST 2023
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_permission.name
     *
     * @mbg.generated Mon Sep 11 03:54:13 AEST 2023
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_permission.ctime
     *
     * @mbg.generated Mon Sep 11 03:54:13 AEST 2023
     */
    private Date ctime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_permission.utime
     *
     * @mbg.generated Mon Sep 11 03:54:13 AEST 2023
     */
    private Date utime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_permission.id
     *
     * @return the value of t_permission.id
     *
     * @mbg.generated Mon Sep 11 03:54:13 AEST 2023
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_permission.id
     *
     * @param id the value for t_permission.id
     *
     * @mbg.generated Mon Sep 11 03:54:13 AEST 2023
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_permission.name
     *
     * @return the value of t_permission.name
     *
     * @mbg.generated Mon Sep 11 03:54:13 AEST 2023
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_permission.name
     *
     * @param name the value for t_permission.name
     *
     * @mbg.generated Mon Sep 11 03:54:13 AEST 2023
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_permission.ctime
     *
     * @return the value of t_permission.ctime
     *
     * @mbg.generated Mon Sep 11 03:54:13 AEST 2023
     */
    public Date getCtime() {
        return ctime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_permission.ctime
     *
     * @param ctime the value for t_permission.ctime
     *
     * @mbg.generated Mon Sep 11 03:54:13 AEST 2023
     */
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_permission.utime
     *
     * @return the value of t_permission.utime
     *
     * @mbg.generated Mon Sep 11 03:54:13 AEST 2023
     */
    public Date getUtime() {
        return utime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_permission.utime
     *
     * @param utime the value for t_permission.utime
     *
     * @mbg.generated Mon Sep 11 03:54:13 AEST 2023
     */
    public void setUtime(Date utime) {
        this.utime = utime;
    }
}