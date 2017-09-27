package com.shinelon.demo.admin.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * <p>
 *
 * </p>
 *
 * @author shinelon
 * @since 2017-09-27
 */
@TableName("sys_user")
public class SysUser extends Model<SysUser> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 手机号码
     */
    private Integer mobile;
    /**
     * 密码
     */
    private String password;
    /**
     * 邮箱地址
     */
    private String email;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 修改时间
     */
    @TableField("update_time")
    private Date updateTime;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    @TableField(exist = false)
    private List<SysRole> roles;

    public Date getCreateTime() {
        return createTime;
    }

    public String getEmail() {
        return email;
    }

    public Long getId() {
        return id;
    }

    public Integer getMobile() {
        return mobile;
    }

    public String getPassword() {
        return password;
    }

    public List<SysRole> getRoles() {
        return roles;
    }

    public Integer getStatus() {
        return status;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public String getUsername() {
        return username;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMobile(Integer mobile) {
        this.mobile = mobile;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    protected Serializable pkVal() {
        return id;
    }
}
