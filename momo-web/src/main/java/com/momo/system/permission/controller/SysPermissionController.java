package com.momo.system.permission.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.momo.result.ResultUtils;
import com.momo.result.ResultVo;
import com.momo.status.CodeStatus;
import com.momo.system.permission.entity.SysPermission;
import com.momo.system.permission.serivce.SysPermissionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * FileName: SysPermissionController
 * Author: zipeng Li
 * Date: 2021/1/28  16:07
 * Description: 权限控制器
 * History:
 * <author>    <time>    <version>    <desc>
 * 作者姓名     修改时间     版本号       描述
 */
@RestController
@RequestMapping("/api/permission")
public class SysPermissionController {
    @Resource
    private SysPermissionService sysPermissionService;

    /**
     * 获取菜单列表
     *
     * @return
     */
    @PostMapping("/getMenuList")
    public ResultVo getMenuList() {
        QueryWrapper<SysPermission> wrapper = new QueryWrapper<>();
        wrapper.lambda().orderByAsc(SysPermission::getOrderNum);
        List<SysPermission> list = sysPermissionService.list(wrapper);
        List<SysPermission> menuList = null;
        if (!list.isEmpty()) {
            menuList = generateTree(list, 0L);
        }
        return ResultUtils.success("菜单列表获取成功", CodeStatus.SUCCESS_CODE, menuList);
    }

    /**
     * 构造菜单树
     * @param menuList
     * @param pid
     * @return
     */
    private List<SysPermission> generateTree(List<SysPermission> menuList, Long pid) {
        // 主菜单
        List<SysPermission> topMenuList = menuList.stream().filter(e -> e.getParentId() == pid).collect(Collectors.toList());
        // 附属菜单
        List<SysPermission> childrenList = menuList.stream().filter(e -> e.getParentId() != pid).collect(Collectors.toList());
        if (topMenuList.size() > 0) {
            topMenuList.forEach(e -> {
                if (childrenList.size() > 0) {
                    generateTree(childrenList, e.getId()).forEach(x -> e.getChildren().add(x));
                }
            });
        }
        return topMenuList;
    }

}
