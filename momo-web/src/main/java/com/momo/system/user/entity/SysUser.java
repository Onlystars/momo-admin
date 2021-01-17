package com.momo.system.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 * FileName: SysUser
 * Author: zipeng Li
 * Date: 2021/1/17  21:22
 * Description: 用户实体类
 * History:
 * <author>    <time>    <version>    <desc>
 * 作者姓名     修改时间     版本号       描述
 */
@Data
@TableName("sys_user")
public class SysUser implements UserDetails, Serializable {
    //主键自动增长
    @TableId(type = IdType.AUTO)
    private Long id;
    //登录名
    private String username;
    //用户名
    private String loginName;
    //登录密码，密码需要加密
    private String password;
    //帐户是否过期(1 未过期，0已过期)
    private boolean isAccountNonExpired = true;
    //帐户是否被锁定(1 未锁定，0已锁定)
    private boolean isAccountNonLocked = true;
    //密码是否过期(1 未过期，0已过期)
    private boolean isCredentialsNonExpired = true;
    //帐户是否可用(1 可用，0 删除用户)
    private boolean isEnabled = true;
    //由于authorities不是数据库里面的字段，所以要排除它，不然mybatis-plus找不到该字段会报错
    @TableField(exist = false)
    Collection<? extends GrantedAuthority> authorities;
    //昵称
    private String nickName;
    //手机号
    private String mobile;
    //邮箱
    private String email;
    //部门id
    private Long deptId;
    //部门名称
    private String deptName;
    //创建时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    //更新时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    //是否是管理员 1：是 0 ：不是
    private String isAdmin;
}
