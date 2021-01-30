package com.momo.result;

/**
 * 返回状态码
 */
public class CodeStatus {
    public static final int SUCCESS_CODE = 200; // 操作成功
    public static final int NO_AUTH = 201; // 无权限
    public static final int TOKEN_ERROR = 202; // token失效
    public static final int ERROR_CODE = 500; // 服务器错误
}
