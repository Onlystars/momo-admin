package com.momo.system.permission.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Slf4j
@TableName(value = "sys_permission")
public class SysPermission {
  @TableId(type = IdType.AUTO)
  private Long id;
  private Long parentId;
  private String parentName;
  private String label;
  private String code;
  private String path;
  private String name;
  private String url;
  private Integer orderNum;
  private String type;
  private String icon;
  private String remark;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date createTime;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date updateTime;
  private Integer isHome;
  //不是数据库的字段需要排除
  @TableField(exist = false)
  private List<SysPermission> children = new ArrayList<>();
}
