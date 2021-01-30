package com.momo.system.user.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.momo.result.ResultVo;
import com.momo.system.user.entity.SysUser;
import com.momo.system.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
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
@RequestMapping("/api/user")
public class UserController {
    public static final String SESSION_KEY = "IMAGE_CODE";
    @Resource
    private UserService userService;
    @Resource
    private DefaultKaptcha defaultKaptcha;
    /**
     * 获取图形验证码
     */
    @RequestMapping("/image")
    public void imageCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //设置以图片的形式响应
        response.setHeader("Cache-Control", "no-store, no-cache");
        //设置页面缓存方式   不缓存，不存储
        response.setContentType("image/jpeg");
        // 1. 获取验证码字符串
        String code = defaultKaptcha.createText();
        log.info("生成的图形验证码是：" + code);
        // 2. 字符串把它放到session中
        request.getSession().setAttribute(SESSION_KEY , code);
        // 3. 获取验证码图片
        BufferedImage image = defaultKaptcha.createImage(code);
        // 4. 将验证码图片把它写出去
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        if (out != null) {
            out.close();
        }
    }

    @PostMapping("/getList")
    public ResultVo getUser(){
        ResultVo<List<SysUser>> resultVo = new ResultVo<>();
        List<SysUser> list = userService.list();
        resultVo.setData(list);
        return resultVo;
    }

}
