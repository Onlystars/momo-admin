package com.momo.system.permission.vo;

import com.momo.system.permission.entity.Permission;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * FileName: MenuVo
 * Author: zipeng Li
 * Date: 2021/1/18  12:44
 * Description: 菜单返回实体
 * History:
 * <author>    <time>    <version>    <desc>
 * 作者姓名     修改时间     版本号       描述
 */
@Data
public class MenuVo implements Serializable {
    private List<Permission> menuList; // 菜单列表
    private List<String> authList; // 权限字符列表
    private List<Permission> routerList; // 路由列表
    private String token; // token
    private Long userId; // 用户id
}
