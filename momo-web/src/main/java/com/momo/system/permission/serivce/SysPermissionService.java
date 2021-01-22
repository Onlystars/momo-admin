package com.momo.system.permission.serivce;

import com.baomidou.mybatisplus.extension.service.IService;
import com.momo.system.permission.entity.SysPermission;

import java.util.List;

/**
 * FileName: SysPermissionService
 * Author: zipeng Li
 * Date: 2021/1/18  13:35
 * Description: sysPermission 服务层接口
 * History:
 * <author>    <time>    <version>    <desc>
 * 作者姓名     修改时间     版本号       描述
 */
public interface SysPermissionService extends IService<SysPermission> {
    /**
     * 根据用户Id查询所有的权限
     * @param userId
     * @return
     */
    List<SysPermission> selectPermissionByUserId(Long userId);

    /**
     * 根据角色id查询所有的权限
     * @param roleId
     * @return
     */
    List<SysPermission> findByRoleId(Long roleId);
}
