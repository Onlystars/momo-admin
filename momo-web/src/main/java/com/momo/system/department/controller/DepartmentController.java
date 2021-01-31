package com.momo.system.department.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.momo.result.ResultUtils;
import com.momo.result.ResultVo;
import com.momo.system.department.entity.Department;
import com.momo.system.department.service.DepartmnetService;
import com.momo.system.department.vo.DepartmentVo;
import com.momo.utils.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * FileName: DepartmentController
 * Author: zipeng Li
 * Date: 2021/1/30  22:11
 * Description:
 * History:
 * <author>    <time>    <version>    <desc>
 * 作者姓名     修改时间     版本号       描述
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/department")
public class DepartmentController {
    @Resource
    private DepartmnetService departmnetService;

    /**
     * 查询部门列表
     * @param departmentVo
     * @return
     */
    @RequestMapping(value = "/getDepartmentList",method = RequestMethod.POST)
    public ResultVo getDepartmentList(@RequestBody DepartmentVo departmentVo){
        //条件构造器
        QueryWrapper<Department> query = new QueryWrapper<>();
        // 根据部门名模糊查询
        if(StringUtils.isNotBlank(departmentVo.getName())){
            query.lambda().like(Department::getName,departmentVo.getName());
        }
        // 根据部门电话查询
        if(StringUtils.isNotBlank(departmentVo.getPhone())){
            query.lambda().eq(Department::getDeptPhone,departmentVo.getPhone());
        }
        // 根据部门pid查询
        query.lambda().eq(Department::getPid,departmentVo.getSelectPid());
        //构造分页
        IPage<Department> page = new Page<>();
        page.setCurrent(departmentVo.getCurrentPage());
        page.setSize(departmentVo.getPageSize());
        IPage<Department> page1 = departmnetService.page(page, query);
        return ResultUtils.success("查询成功",page1);
    }

    /**
     * 新增部门
     * @return
     */
    @RequestMapping(value = "/addDepartment",method = RequestMethod.POST)
    public ResultVo addDepartment(@RequestBody Department department){
        String id = UUIDUtil.getUniqueIdByUUId();
        department.setId(id);
        boolean b = departmnetService.save(department);
        if(b){
            return ResultUtils.success("新增部门成功");
        }else{
            return ResultUtils.error("新增部门失败");
        }
    }

    /**
     * 根据id查询部门数据
     * @return
     */
    @RequestMapping(value = "/getDepartmentById",method = RequestMethod.GET)
    public ResultVo getDepartmentById(@RequestParam String id){
        Department byId = departmnetService.getById(id);
        return ResultUtils.success("查询成功",byId);
    }

    /**
     * 更新部门
     * @return
     */
    @RequestMapping(value = "/updateDepartment",method = RequestMethod.POST)
    public ResultVo updateDepartment(@RequestBody Department department){
        boolean b = departmnetService.updateById(department);
        if(b){
            return ResultUtils.success("更新部门成功");
        }else{
            return ResultUtils.error("更新部门失败");
        }
    }

    /**
     * 删除部门
     * @return
     */
    @RequestMapping(value = "/deleteById",method = RequestMethod.GET)
    public ResultVo deleteById(@RequestParam String id){
        boolean b = departmnetService.removeById(id);
        if(b){
            return ResultUtils.success("删除部门成功");
        }else{
            return ResultUtils.error("删除部门失败");
        }
    }

    /**
     * 获取左侧部门树
     * @return
     */
    @RequestMapping(value = "/getLeftTree",method = RequestMethod.GET)
    public ResultVo getLeftTree(){
        List<Department> list = departmnetService.list();
        return ResultUtils.success("查询成功",list);

    }

    /**
     * 新增部门获取上级部门树
     * @return
     */
    @RequestMapping(value = "/getParentTree",method = RequestMethod.GET)
    public ResultVo getParentTree(){
        List<Department> list = departmnetService.list();
        Department sysDept = new Department();
        sysDept.setId("0");
        sysDept.setPid("-1");
        sysDept.setName("顶级部门");
        sysDept.setLikeId("0,");
        list.add(0,sysDept);
        return ResultUtils.success("查询成功",list);

    }
}
