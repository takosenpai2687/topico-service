package usyd.elec5619.topicoservice.model;

import java.util.Date;

public class RolePermission {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_role_permission.id
     *
     * @mbg.generated Mon Sep 11 03:49:05 AEST 2023
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_role_permission.role_id
     *
     * @mbg.generated Mon Sep 11 03:49:05 AEST 2023
     */
    private Integer roleId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_role_permission.permission_id
     *
     * @mbg.generated Mon Sep 11 03:49:05 AEST 2023
     */
    private Integer permissionId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_role_permission.ctime
     *
     * @mbg.generated Mon Sep 11 03:49:05 AEST 2023
     */
    private Date ctime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_role_permission.utime
     *
     * @mbg.generated Mon Sep 11 03:49:05 AEST 2023
     */
    private Date utime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_role_permission.id
     *
     * @return the value of t_role_permission.id
     *
     * @mbg.generated Mon Sep 11 03:49:05 AEST 2023
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_role_permission.id
     *
     * @param id the value for t_role_permission.id
     *
     * @mbg.generated Mon Sep 11 03:49:05 AEST 2023
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_role_permission.role_id
     *
     * @return the value of t_role_permission.role_id
     *
     * @mbg.generated Mon Sep 11 03:49:05 AEST 2023
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_role_permission.role_id
     *
     * @param roleId the value for t_role_permission.role_id
     *
     * @mbg.generated Mon Sep 11 03:49:05 AEST 2023
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_role_permission.permission_id
     *
     * @return the value of t_role_permission.permission_id
     *
     * @mbg.generated Mon Sep 11 03:49:05 AEST 2023
     */
    public Integer getPermissionId() {
        return permissionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_role_permission.permission_id
     *
     * @param permissionId the value for t_role_permission.permission_id
     *
     * @mbg.generated Mon Sep 11 03:49:05 AEST 2023
     */
    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_role_permission.ctime
     *
     * @return the value of t_role_permission.ctime
     *
     * @mbg.generated Mon Sep 11 03:49:05 AEST 2023
     */
    public Date getCtime() {
        return ctime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_role_permission.ctime
     *
     * @param ctime the value for t_role_permission.ctime
     *
     * @mbg.generated Mon Sep 11 03:49:05 AEST 2023
     */
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_role_permission.utime
     *
     * @return the value of t_role_permission.utime
     *
     * @mbg.generated Mon Sep 11 03:49:05 AEST 2023
     */
    public Date getUtime() {
        return utime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_role_permission.utime
     *
     * @param utime the value for t_role_permission.utime
     *
     * @mbg.generated Mon Sep 11 03:49:05 AEST 2023
     */
    public void setUtime(Date utime) {
        this.utime = utime;
    }
}