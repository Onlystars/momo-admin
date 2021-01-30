package com.momo.system.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.momo.system.user.entity.SysUser;
import com.momo.system.user.mapper.UserMapper;
import com.momo.system.user.service.UserService;
import org.springframework.stereotype.Service;

/**
 * FileName: SysUserServiceImpl
 * Author: zipeng Li
 * Date: 2021/1/17  21:30
 * Description:
 * History:
 * <author>    <time>    <version>    <desc>
 * 作者姓名     修改时间     版本号       描述
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, SysUser> implements UserService {

    @Override
    public SysUser getSysUserByUserName(String username) {
        QueryWrapper<SysUser> query = new QueryWrapper<>();
        query.lambda().eq(SysUser::getUsername, username);
        return this.baseMapper.selectOne(query);
    }
}
