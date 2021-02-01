package com.momo.system.role.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.momo.result.ResultUtils;
import com.momo.result.ResultVo;
import com.momo.system.permission.entity.Permission;
import com.momo.system.permission.serivce.PermissionService;
import com.momo.system.permission.vo.TreeVo;
import com.momo.system.role.entity.SysRole;
import com.momo.system.role.service.RoleService;
import com.momo.system.role.vo.PerVo;
import com.momo.system.role.vo.RoleParm;
import com.momo.system.role_permission.service.RolePermissionService;
import com.momo.system.role_permission.vo.PermissionRoleParmVo;
import com.momo.system.user.entity.SysUser;
import com.momo.system.user.service.UserService;
import com.momo.system.user_role.entity.UserRole;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * FileName: RoleController
 * Author: zipeng Li
 * Date: 2021/1/30  16:27
 * Description:
 * History:
 * <author>    <time>    <version>    <desc>
 * 作者姓名     修改时间     版本号       描述
 */
@RestController
@RequestMapping("/api/role")
public class RoleController {
    @Resource
    private RoleService roleService;
    @Resource
    private UserService userService;
    @Resource
    private PermissionService permissionService;
    @Resource
    private RolePermissionService rolePermissionService;

    /**
     * 新增角色
     *
     * @param role
     * @return
     */
    @RequestMapping(value = "addRole", method = RequestMethod.POST)
    public ResultVo addRole(@RequestBody SysRole role) {
        boolean b = roleService.save(role);
        if (b) {
            return ResultUtils.success("新增成功!");
        } else {
            return ResultUtils.error("新增失败！");
        }
    }

    /**
     * 根据id查询角色
     *
     * @return
     */
    @RequestMapping(value = "/getRoleById", method = RequestMethod.GET)
    public ResultVo getRoleById(@RequestParam Integer id) {
        SysRole role = roleService.getById(id);
        return ResultUtils.success("成功", role);

    }

    /**
     * 编辑角色
     *
     * @return
     */
    @RequestMapping(value = "/updateRole", method = RequestMethod.POST)
    public ResultVo updateRole(@RequestBody SysRole sysRole) {
        boolean b = roleService.updateById(sysRole);
        if (b) {
            return ResultUtils.success("编辑角色成功!");
        } else {
            return ResultUtils.error("编辑角色失败!");
        }
    }

    /**
     * 删除角色
     *
     * @return
     */
    @RequestMapping(value = "/deleteRole", method = RequestMethod.POST)
    public ResultVo deleteRole(@RequestBody SysRole sysRole) {
        boolean b = roleService.removeById(sysRole.getId());
        if (b) {
            return ResultUtils.success("删除角色成功!");
        } else {
            return ResultUtils.error("删除角色失败!");
        }
    }

    /**
     * 查询角色列表
     *
     * @param parmVo
     * @return
     */
    @RequestMapping(value = "/getRoleList", method = RequestMethod.POST)
    public ResultVo getRoleList(@RequestBody RoleParm parmVo) {
        QueryWrapper<SysRole> query = new QueryWrapper<>();
        if (!StringUtils.isBlank(parmVo.getTitle())) {
            query.lambda().like(SysRole::getName, parmVo.getTitle());
        }
        IPage<SysRole> page = new Page();
        page.setSize(parmVo.getPageSize());
        page.setCurrent(parmVo.getCurrentPage());
        IPage<SysRole> roleList = roleService.page(page, query);
        return ResultUtils.success("查询成功", roleList);
    }

    /**
     * 分配角色角色列表
     *
     * @return
     */
    @RequestMapping(value = "/getRolistForAssing", method = RequestMethod.GET)
    public ResultVo getRolistForAssing() {
        List<SysRole> list = roleService.list();
        return ResultUtils.success("查询成功", list);
    }

    /**
     * 根据用户id查询角色id
     *
     * @return
     */
    @RequestMapping(value = "/getRoleIdByUserId", method = RequestMethod.POST)
    public ResultVo getRoleIdByUserId(@RequestBody UserRole userRole) {
        UserRole roleIdByUserId = roleService.getRoleIdByUserId(userRole.getUserId());
        return ResultUtils.success("查询成功", roleIdByUserId);
    }

    /**
     * 分配角色保存
     *
     * @return
     */
    @RequestMapping(value = "/assingRole", method = RequestMethod.POST)
    public ResultVo assingRole(@RequestBody UserRole userRole) {
        roleService.assingRole(userRole);
        return ResultUtils.success("分配角色成功");
    }

    /**
     * 分配权限树查询
     * @return
     */
    @RequestMapping(value = "/permissionTree",method = RequestMethod.POST)
    public ResultVo permissionTree(@RequestBody PerVo perVo){
        Long userId = perVo.getUserId();
        List<Permission> permission = null;
        //1.查询当前用户的所有权限
        SysUser user = userService.getById(userId);
        if(StringUtils.isNotEmpty(user.getIsAdmin()) && user.getIsAdmin().equals("1")){
            permission = permissionService.list();
        }else{
            permission = permissionService.selectPermissionByUserId(userId);
        }
        //查询角色原来的数据，设置为选中
        List<Permission> permissionListByRoleId = permissionService.findByRoleId(perVo.getRoleId());
        //组装成树数据
        List<TreeVo> listTree = new ArrayList<>();
        if(permission != null){
            for(int i =0;i< permission.size();i++){
                if(permission.get(i) != null){
                    TreeVo tree = new TreeVo();
                    tree.setId(permission.get(i).getId());
                    tree.setPid(permission.get(i).getParentId());
                    tree.setName(permission.get(i).getLabel());
                    if(permissionListByRoleId.size() >0){
                        for(int j=0;j < permissionListByRoleId.size();j++){
                            if(permission.get(i).getId().equals(permissionListByRoleId.get(j).getId())){
                                tree.setChecked(true);
                                break;
                            }
                        }
                    }
                    listTree.add(tree);
                }
            }
        }
        return ResultUtils.success("查询成功",listTree);
    }

    //保存权限
    @RequestMapping(value = "/saveAssignRole",method = RequestMethod.POST)
    public ResultVo saveAssignRole(@RequestBody PermissionRoleParmVo parmVo){
        if(parmVo != null && !parmVo.getList().isEmpty()){
            List<TreeVo> list = parmVo.getList();
            Long roleId = parmVo.getRoleId();
            List<Long> ids = list.stream().filter(item -> item != null).map(item -> item.getId()).collect(Collectors.toList());
            rolePermissionService.saveAssignRole(roleId,ids);
            return ResultUtils.success("分配成功!");
        }else{
            return ResultUtils.error("请选择权限!");
        }
    }
}
