package com.momo.system.role.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.momo.system.role.entity.SysRole;
import com.momo.system.role.mapper.RoleMapper;
import com.momo.system.role.service.RoleService;
import com.momo.system.user_role.entity.UserRole;
import com.momo.system.user_role.mapper.UserRoleMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * FileName: RoleServiceImpl
 * Author: zipeng Li
 * Date: 2021/1/30  16:26
 * Description:
 * History:
 * <author>    <time>    <version>    <desc>
 * 作者姓名     修改时间     版本号       描述
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, SysRole> implements RoleService {
    @Resource
    private UserRoleMapper userRoleMapper;

    @Override
    public UserRole getRoleIdByUserId(Long userId) {
        return userRoleMapper.getRoleIdByUserId(userId);
    }

    @Override
    public void assingRole(UserRole userRole) {
        //先删除该用户原来的角色
        QueryWrapper<UserRole> query = new QueryWrapper<>();
        query.lambda().eq(UserRole::getUserId,userRole.getUserId());
        userRoleMapper.delete(query);
        userRoleMapper.insert(userRole);
    }
}
