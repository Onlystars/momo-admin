package com.momo.system.permission.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.momo.system.permission.entity.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * FileName: PermissionMapper
 * Author: zipeng Li
 * Date: 2021/1/18  13:32
 * Description: 权限管理 DAO层
 * History:
 * <author>    <time>    <version>    <desc>
 * 作者姓名     修改时间     版本号       描述
 */
public interface PermissionMapper extends BaseMapper<Permission> {
    /**
     * 根据用户Id查询所有的权限
     * @param userId
     * @return
     */
    List<Permission> selectPermissionByUserId(@Param("userId") Long userId);

    /**
     * 根据角色id查询所有的权限
     * @param roleId
     * @return
     */
    List<Permission> findByRoleId(@Param("roleId") Long roleId);
}
