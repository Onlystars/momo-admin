package com.momo.system.permission.serivce.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.momo.system.permission.entity.Permission;
import com.momo.system.permission.mapper.PermissionMapper;
import com.momo.system.permission.serivce.PermissionService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * FileName: SysPermissionServiceImpl
 * Author: zipeng Li
 * Date: 2021/1/18  13:37
 * Description: SysPermission 服务层实现类
 * History:
 * <author>    <time>    <version>    <desc>
 * 作者姓名     修改时间     版本号       描述
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {
    @Override
//    @Cacheable(value = "permissions",key = "#userId")
    public List<Permission> selectPermissionByUserId(Long userId) {
        return this.baseMapper.selectPermissionByUserId(userId);
    }

    @Override
//    @Cacheable(value = "permissions",key = "#roleId")
    public List<Permission> findByRoleId(Long roleId) {
        return this.baseMapper.findByRoleId(roleId);
    }
}
