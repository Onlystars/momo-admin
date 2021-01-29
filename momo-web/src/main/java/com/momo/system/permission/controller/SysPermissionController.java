package com.momo.system.permission.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.momo.result.ResultUtils;
import com.momo.result.ResultVo;
import com.momo.status.CodeStatus;
import com.momo.system.permission.Vo.TreeVo;
import com.momo.system.permission.entity.SysPermission;
import com.momo.system.permission.serivce.SysPermissionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
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
    @GetMapping("/getMenuList")
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

    /**
     * 新增权限
     * @param sysPermission
     * @return
     */
    @PostMapping("/addPermission")
    public ResultVo addPermission(@RequestBody SysPermission sysPermission){
        sysPermissionService.save(sysPermission);
        return ResultUtils.success("新增权限成功");
    }

    /**
     * 新增权限时，获取上级菜单树
     * @return
     */
    @GetMapping("/getParentTree")
    public ResultVo getParentTree(){
        QueryWrapper<SysPermission> query = new QueryWrapper<>();
        query.lambda().eq(SysPermission::getType, "0").or().eq(SysPermission::getType, "1");
        List<SysPermission> list = sysPermissionService.list(query);
        ArrayList<TreeVo> listTree = new ArrayList<>();
        TreeVo parentTree = new TreeVo();
        parentTree.setId(0L);
        parentTree.setPid(-1L);
        parentTree.setName("顶级菜单");
        parentTree.setOpen(true);
        parentTree.setChecked(false);
        listTree.add(parentTree);
        if(list.size()>0){
            for(SysPermission p:list){
                if(p!=null){
                    TreeVo tree = new TreeVo();
                    tree.setId(p.getId());
                    tree.setPid(p.getParentId());
                    tree.setName(p.getLabel());
                    tree.setOpen(true);
                    tree.setChecked(false);
                    listTree.add(tree);
                }
            }
        }
        return ResultUtils.success("获取上级菜单树成功",listTree);
    }

    /**
     * 根据permission id查询菜单
     * @param id
     * @return
     */
    @PostMapping("/getMenuById")
    public ResultVo getMenuById(@RequestParam Integer id){
        SysPermission menu = sysPermissionService.getById(id);
        return ResultUtils.success("菜单查询成功",menu);
    }

    /**
     * 根据id更新权限
     * @param permission
     * @return
     */
    @PostMapping("/editSave")
    public ResultVo editSave(@RequestBody SysPermission permission){
        permission.setCreateTime(new Date());
        boolean res = sysPermissionService.updateById(permission);
        if(res){
            return ResultUtils.success("更新成功");
        }else{
            return ResultUtils.error("更新失败");
        }

    }

    /**
     * 删除权限
     * @return
     */
    @PostMapping("/deleteEntity")
    public ResultVo deleteEntity(@RequestBody SysPermission permission){

        boolean b = sysPermissionService.removeById(permission.getId());
        if(b){
            return ResultUtils.success("删除成功!");
        }else{
            return ResultUtils.error("删除失败!");
        }

    }

}
