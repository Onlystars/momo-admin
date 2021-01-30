package com.momo.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * FileName: TokenException
 * Author: zipeng Li
 * Date: 2021/1/30  14:47
 * Description: Token异常处理类
 * History:
 * <author>    <time>    <version>    <desc>
 * 作者姓名     修改时间     版本号       描述
 */
public class TokenException extends AuthenticationException {

    public TokenException(String msg) {
        super(msg);
    }
}
