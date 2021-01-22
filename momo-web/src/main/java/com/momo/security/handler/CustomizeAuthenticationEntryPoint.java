package com.momo.security.handler;

import com.alibaba.fastjson.JSON;
import com.momo.result.ResultUtils;
import com.momo.status.CodeStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * FileName: CustomizeAuthenticationEntryPoint
 * Author: zipeng Li
 * Date: 2021/1/18  13:12
 * Description: 匿名用户访问无权限处理器
 * History:
 * <author>    <time>    <version>    <desc>
 * 作者姓名     修改时间     版本号       描述
 */
@Component("customizeAuthenticationEntryPoint")
public class CustomizeAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setContentType("text/json;charset=utf-8");
        httpServletResponse.getWriter().write(JSON.toJSONString(ResultUtils.error("用户未登录", CodeStatus.NO_AUTH)));
    }
}
