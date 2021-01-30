package com.momo.security.exception;


import org.springframework.security.core.AuthenticationException;

/**
 * FileName: ImageCodeException
 * Author: zipeng Li
 * Date: 2021/1/18  13:26
 * Description: 验证码验证失败异常类
 * History:
 * <author>    <time>    <version>    <desc>
 * 作者姓名     修改时间     版本号       描述
 */
public class ImageCodeException extends AuthenticationException {
    public ImageCodeException(String msg) {
        super(msg);
    }
}
