package com.momo.system.role_permission.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.momo.system.role_permission.entity.RolePermission;

import java.util.List;

/**
 * FileName: RolePermissionService
 * Author: zipeng Li
 * Date: 2021/2/1  15:45
 * Description:
 * History:
 * <author>    <time>    <version>    <desc>
 * 作者姓名     修改时间     版本号       描述
 */
public interface RolePermissionService extends IService<RolePermission> {
    /**
     * 分配权限保存
     * @param
     */
    void saveAssignRole(Long roleId, List<Long> collect);
}
