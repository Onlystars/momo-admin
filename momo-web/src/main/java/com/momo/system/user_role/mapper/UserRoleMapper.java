package com.momo.system.user_role.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.momo.system.user_role.entity.UserRole;
import org.apache.ibatis.annotations.Param;

/**
 * FileName: UserRoleMapper
 * Author: zipeng Li
 * Date: 2021/2/1  14:36
 * Description:
 * History:
 * <author>    <time>    <version>    <desc>
 * 作者姓名     修改时间     版本号       描述
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {
    UserRole getRoleIdByUserId(@Param("userId") Long userId);
}
