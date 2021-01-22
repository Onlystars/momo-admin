package com.momo.security.detailservice;

import com.momo.system.permission.entity.SysPermission;
import com.momo.system.permission.serivce.SysPermissionService;
import com.momo.system.user.entity.SysUser;
import com.momo.system.user.service.SysUserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * FileName: CustomerUserDetailsService
 * Author: zipeng Li
 * Date: 2021/1/18  12:28
 * Description: 认证处理类
 * History:
 * <author>    <time>    <version>    <desc>
 * 作者姓名     修改时间     版本号       描述
 */
@Service
public class CustomerUserDetailsService implements UserDetailsService {

    @Resource
    private SysUserService sysUserService;

    @Resource
    private SysPermissionService sysPermissionService;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //1.查询用户信息
        SysUser sysUser = sysUserService.getSysUserByUserName(username);
        //2.用户不存在抛出异常
        if(null == sysUser){
            throw new UsernameNotFoundException("用户名或密码错误!");
        }
        //3.查询用户权限，设置到SysUser 的 authorities 中
        List<SysPermission> permissions = sysPermissionService.selectPermissionByUserId(sysUser.getId());
        //4.获取code字段
        List<String> collect = permissions.stream().filter(item -> item != null).map(item -> item.getCode()).collect(Collectors.toList());
        //5.转成数组
        String[] codes  = collect.toArray(new String[collect.size()]);
        //6.把codes转成List<GrantedAuthority>
        List<GrantedAuthority> authorityList = AuthorityUtils.createAuthorityList(codes);
        //7.设置权限
        sysUser.setAuthorities(authorityList);
        //8.设置用户所有菜单
        sysUser.setPermissionList(permissions);
        return sysUser;
    }
}
