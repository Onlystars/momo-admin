package com.momo.system.user.controller;

import com.momo.result.ResultVo;
import com.momo.system.user.entity.SysUser;
import com.momo.system.user.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * FileName: SysUserController
 * Author: zipeng Li
 * Date: 2021/1/17  21:31
 * Description:
 * History:
 * <author>    <time>    <version>    <desc>
 * 作者姓名     修改时间     版本号       描述
 */
@Slf4j
@RestController
@RequestMapping("/system/user")
public class SysUserController {
    @Resource
    private SysUserService sysUserService;

    @RequestMapping(value = "getUser", method = RequestMethod.GET)
    public ResultVo getUser(){
        ResultVo<List<SysUser>> resultVo = new ResultVo<>();
        List<SysUser> list = sysUserService.list();
        resultVo.setData(list);
        return resultVo;
    }

}
