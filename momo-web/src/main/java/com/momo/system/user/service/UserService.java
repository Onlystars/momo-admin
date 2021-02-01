package com.momo.system.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.momo.system.user.entity.SysUser;
import com.momo.system.user_role.entity.UserRole;

/**
 * FileName: SysUserService
 * Author: zipeng Li
 * Date: 2021/1/17  21:28
 * Description: service层 接口
 * History:
 * <author>    <time>    <version>    <desc>
 * 作者姓名     修改时间     版本号       描述
 */
public interface UserService extends IService<SysUser> {
    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    SysUser getSysUserByUserName(String username);
}
