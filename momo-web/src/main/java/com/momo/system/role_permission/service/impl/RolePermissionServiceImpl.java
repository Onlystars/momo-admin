package com.momo.system.role_permission.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.momo.system.role_permission.entity.RolePermission;
import com.momo.system.role_permission.mapper.RolePermissionMapper;
import com.momo.system.role_permission.service.RolePermissionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * FileName: RolePermissionServiceImpl
 * Author: zipeng Li
 * Date: 2021/2/1  15:45
 * Description:
 * History:
 * <author>    <time>    <version>    <desc>
 * 作者姓名     修改时间     版本号       描述
 */
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements RolePermissionService {

    @Override
    @Transactional
    public void saveAssignRole(Long roleId, List<Long> ids) {
        //1.删除原来角色的权限
        QueryWrapper<RolePermission> query = new QueryWrapper<>();
        query.lambda().eq(RolePermission::getRoleId,roleId);
        this.baseMapper.delete(query);
        //2.插入新权限
        this.baseMapper.saveRolePermissions(roleId,ids);
    }
}
