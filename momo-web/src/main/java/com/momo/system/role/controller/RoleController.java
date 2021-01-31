package com.momo.system.role.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.momo.result.ResultUtils;
import com.momo.result.ResultVo;
import com.momo.system.role.entity.SysRole;
import com.momo.system.role.service.RoleService;
import com.momo.system.role.vo.RoleParm;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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

    /**
     * 新增角色
     * @param role
     * @return
     */
    @RequestMapping(value = "addRole",method = RequestMethod.POST)
    public ResultVo addRole(@RequestBody SysRole role){
        boolean b = roleService.save(role);
        if(b){
            return ResultUtils.success("新增成功!");
        }else{
            return ResultUtils.error("新增失败！");
        }
    }

    /**
     * 根据id查询角色
     * @return
     */
    @RequestMapping(value = "/getRoleById",method = RequestMethod.GET)
    public ResultVo getRoleById(@RequestParam Integer id){
        SysRole role = roleService.getById(id);
        return ResultUtils.success("成功",role);

    }

    /**
     * 编辑角色
     * @return
     */
    @RequestMapping(value = "/updateRole",method = RequestMethod.POST)
    public ResultVo updateRole(@RequestBody SysRole sysRole){
        boolean b = roleService.updateById(sysRole);
        if(b){
            return ResultUtils.success("编辑角色成功!");
        }else{
            return ResultUtils.error("编辑角色失败!");
        }
    }

    /**
     * 删除角色
     * @return
     */
    @RequestMapping(value = "/deleteRole",method = RequestMethod.POST)
    public ResultVo deleteRole(@RequestBody SysRole sysRole){
        boolean b = roleService.removeById(sysRole.getId());
        if(b){
            return ResultUtils.success("删除角色成功!");
        }else{
            return ResultUtils.error("删除角色失败!");
        }
    }

    /**
     * 查询角色列表
     * @param parmVo
     * @return
     */
    @RequestMapping(value = "/getRoleList",method = RequestMethod.POST)
    public ResultVo getRoleList(@RequestBody RoleParm parmVo){
        QueryWrapper<SysRole> query = new QueryWrapper<>();
        if(!StringUtils.isBlank(parmVo.getTitle())){
            query.lambda().like(SysRole::getName,parmVo.getTitle());
        }
        IPage<SysRole> page = new Page();
        page.setSize(parmVo.getPageSize());
        page.setCurrent(parmVo.getCurrentPage());
        IPage<SysRole> roleList = roleService.page(page, query);
        return ResultUtils.success("查询成功",roleList);
    }


}
