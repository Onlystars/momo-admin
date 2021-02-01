package com.momo.system.role_permission.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * FileName: RolePermission
 * Author: zipeng Li
 * Date: 2021/2/1  15:34
 * Description:
 * History:
 * <author>    <time>    <version>    <desc>
 * 作者姓名     修改时间     版本号       描述
 */
@Data
@TableName(value = "sys_role_permission")
public class RolePermission implements Serializable {
    @TableId(type= IdType.AUTO)
    private Long id;
    private Long roleId;
    private Long permissionId;
}
