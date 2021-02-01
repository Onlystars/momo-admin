package com.momo.system.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.momo.result.ResultUtils;
import com.momo.result.ResultVo;
import com.momo.system.user.entity.SysUser;
import com.momo.system.user.service.UserService;
import com.momo.system.user.vo.UserParm;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

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
@RequestMapping("/api/user")
public class UserController {
    public static final String SESSION_KEY = "IMAGE_CODE";
    @Resource
    private UserService userService;
    @Resource
    private DefaultKaptcha defaultKaptcha;
    @Resource
    private PasswordEncoder passwordEncoder;

    // @GetMapping(value = "/getList")
    // public ResultVo getList(){
    //     List<SysUser> list = userService.list();
    //     return ResultUtils.success("查询成功",list);
    // }
    @RequestMapping("/image")
    public void imageCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //设置页面缓存方式   不缓存，不存储
        response.setHeader("Cache-Control","no-store, no-cache");
        //设置以图片的形式响应
        response.setContentType("image/jpeg");
        //1.获取验证码字符
        String text = defaultKaptcha.createText();
        //2.存储到session中
        request.getSession().setAttribute(SESSION_KEY,text);
        //3.生成验证码图片
        BufferedImage image = defaultKaptcha.createImage(text);
        //4.输出给前端
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image,"jpg",out);
        if(out != null){
            out.close();
        }
    }
    //查询用户列表
    @RequestMapping(value = "/getUserList",method = RequestMethod.POST)
    public ResultVo getUserList(@RequestBody UserParm parm){
        QueryWrapper<SysUser> query  =new QueryWrapper<>();
        if(StringUtils.isNotBlank(parm.getLoginName())){
            query.lambda().eq(SysUser::getLoginName,parm.getLoginName());
        }
        if(StringUtils.isNotBlank(parm.getMobile())){
            query.lambda().eq(SysUser::getMobile,parm.getMobile());
        }
        query.lambda().eq(SysUser::getDeptId,parm.getDeptId());
        IPage<SysUser> page = new Page<>();
        page.setCurrent(parm.getCurrentPage());
        page.setSize(parm.getPageSize());
        IPage<SysUser> userIPage = userService.page(page, query);
        return ResultUtils.success("查询成功",userIPage);
    }

    /**
     * 新增用户
     * @return
     */
    @PreAuthorize("hasAuthority('sys:user:add')")
    @RequestMapping(value = "addUser",method = RequestMethod.POST)
    public ResultVo addUser(@RequestBody SysUser user){
        QueryWrapper<SysUser> query = new QueryWrapper<>();
        query.lambda().eq(SysUser::getUsername,user.getUsername());
        //查询用户是否存在
        SysUser one = userService.getOne(query);
        if(one != null){
            return ResultUtils.error("用户名已经存在!");
        }
        //加密用户密码
        String pwd = passwordEncoder.encode(user.getPassword());
        user.setPassword(pwd);
        boolean b = userService.save(user);
        if(b){
            return ResultUtils.success("新增用户成功");
        }else{
            return ResultUtils.error("新增用户失败");
        }
    }

    /**
     * 根据用户id查询用户
     * @return
     */
    @RequestMapping(value = "getUserById",method = RequestMethod.GET)
    public ResultVo getUserById(@RequestParam Integer id){
        SysUser sysUser = userService.getById(id);
        return ResultUtils.success("查询成功",sysUser);
    }

    /**
     * 编辑用户保存
     * @return
     */
    @RequestMapping(value = "updateSaveUser",method = RequestMethod.POST)
    public ResultVo updateSaveUser(@RequestBody SysUser user){
        //判断用户是否存在
        QueryWrapper<SysUser> query = new QueryWrapper<>();
        query.lambda().eq(SysUser::getUsername,user.getUsername());
        SysUser one = userService.getOne(query);
        Long id = one.getId();//查询出来的id
        Long editId = user.getId();//编辑的用户id
        if(!id.equals(editId)){
            return ResultUtils.error("用户名已经存在!");
        }
        boolean b = userService.updateById(user);
        if(b){
            return ResultUtils.success("编辑成功");
        }else{
            return ResultUtils.error("编辑失败");
        }
    }

    /**
     * 根据用户id删除
     * @return
     */
    @RequestMapping(value = "deleteUserById",method = RequestMethod.GET)
    public ResultVo deleteUserById(@RequestParam Integer id){
        boolean b = userService.removeById(id);
        if(b){
            return ResultUtils.success("删除用户成功");
        }else{
            return ResultUtils.error("删除用户失败");
        }
    }

}
