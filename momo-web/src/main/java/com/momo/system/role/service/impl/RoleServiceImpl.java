package com.momo.system.role.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.momo.system.role.entity.SysRole;
import com.momo.system.role.mapper.RoleMapper;
import com.momo.system.role.service.RoleService;
import org.springframework.stereotype.Service;

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
}
