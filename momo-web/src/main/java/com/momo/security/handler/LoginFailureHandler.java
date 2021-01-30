package com.momo.security.handler;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.momo.result.CodeStatus;
import com.momo.result.ResultUtils;
import com.momo.security.exception.ImageCodeException;
import com.momo.security.exception.TokenException;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * FileName: LoginFailureHandler
 * Author: zipeng Li
 * Date: 2021/1/18  12:58
 * Description: 登录失败返回处理
 * History:
 * <author>    <time>    <version>    <desc>
 * 作者姓名     修改时间     版本号       描述
 */
@Component("loginFailureHandler")
public class LoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        ServletOutputStream out = httpServletResponse.getOutputStream();
        String str = null;
        int code = 500;
        if (e instanceof AccountExpiredException) {
            //账号过期
            str = "账户过期，登录失败!";
        } else if (e instanceof BadCredentialsException) {
            //密码错误
            str = "用户名或密码输入错误，登录失败!";
        } else if (e instanceof CredentialsExpiredException) {
            //密码过期
            str = "密码过期，登录失败!";
        } else if (e instanceof DisabledException) {
            //账号不可用
            str = "账户被禁用，登录失败!";
        } else if (e instanceof LockedException) {
            //账号锁定
            str = "账户被锁定，登录失败!";
        } else if (e instanceof InternalAuthenticationServiceException) {
            //用户不存在
            str = "用户不存在";
        }else if(e instanceof ImageCodeException){
            str = e.getMessage();
        }else if(e instanceof TokenException){
            //token异常
            code = 202;
            str = e.getMessage();
        }else{
            //其他错误
            str = "登录失败!";
        }
        String rstr;
        if(code == 202){
            rstr = JSONObject.toJSONString(ResultUtils.error(str, CodeStatus.TOKEN_ERROR), SerializerFeature.DisableCircularReferenceDetect);
        }else{
            rstr = JSONObject.toJSONString(ResultUtils.error(str), SerializerFeature.DisableCircularReferenceDetect);
        }
        out.write(rstr.getBytes("UTF-8"));
        out.flush();
        out.close();
    }
}
