package com.momo.system.department.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName(value = "sys_dept")
public class Department implements Serializable {
    //主键
    @TableId
    private String id;
    //上级部门id
    private String pid;
    //上级部门id集合
    private String likeId;
    //上级部门名称
    private String parentName;
    //部门经理
    private String manager;
    //部门名称
    private String name;
    //部门编码
    private String deptCode;
    //部门地址
    private String deptAddress;
    //部门电话
    private String deptPhone;
    //序号
    private Integer orderNum;
}