package com.momo.system.permission.Vo;

import com.momo.system.permission.entity.SysPermission;
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
    private List<SysPermission> menuList;
    private List<String> authList;
    private List<SysPermission> routerList;
    private String token;
    private Long userId;
}
