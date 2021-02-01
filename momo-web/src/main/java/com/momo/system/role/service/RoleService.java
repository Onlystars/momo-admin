package com.momo.system.role.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.momo.system.role.entity.SysRole;
import com.momo.system.user_role.entity.UserRole;

/**
 * FileName: RoleService
 * Author: zipeng Li
 * Date: 2021/1/30  16:25
 * Description: 服务层接口
 * History:
 * <author>    <time>    <version>    <desc>
 * 作者姓名     修改时间     版本号       描述
 */
public interface RoleService extends IService<SysRole> {
    /**
     * 根据用户id查询角色id
     *
     * @return
     */
    UserRole getRoleIdByUserId(Long userId);

    /**
     * 分配权限
     *
     * @param userRole
     * @return
     */
    void assingRole(UserRole userRole);
}
