package com.momo.result;

/**
 * FileName: ResultUtils
 * Author: zipeng Li
 * Date: 2021/1/17  21:35
 * Description: 数据返回工具类
 * History:
 * <author>    <time>    <version>    <desc>
 * 作者姓名     修改时间     版本号       描述
 */
public class ResultUtils {
    /**
     * 无参数返回
     * @return
     */
    public static ResultVo succcess() {
        return Vo(null, CodeStatus.SUCCESS_CODE, null);
    }

    /**
     * 带消息返回
     * @param msg
     * @return
     */
    public static ResultVo success(String msg){
        return Vo(msg, CodeStatus.SUCCESS_CODE,null);
    }
    /**
     * 返回带参数
     * @param msg
     * @param data
     * @return
     */
    public static ResultVo success(String msg,Object data){
        return Vo(msg, CodeStatus.SUCCESS_CODE,data);
    }
    public static ResultVo success(String msg,int code,Object data){
        return Vo(msg,code,data);
    }
    public static ResultVo Vo(String msg, int code, Object data) {
        return new ResultVo(msg, code, data);
    }

    /**
     * 错误返回
     * @return
     */
    public static ResultVo error(){
        return Vo(null, CodeStatus.ERROR_CODE,null);
    }
    public static ResultVo error(String msg){
        return Vo(msg, CodeStatus.ERROR_CODE,null);
    }
    public static ResultVo error(String msg,int code,Object data){
        return Vo(msg,code,data);
    }
    public static ResultVo error(String msg,int code){
        return Vo(msg,code,null);
    }
    public static ResultVo error(String msg,Object data){
        return Vo(msg, CodeStatus.ERROR_CODE,data);
    }
    public static ResultPageVo success(String msg, Integer pageNum, Integer pageSize, Integer total, Object data){
        return new ResultPageVo(null, CodeStatus.SUCCESS_CODE,pageNum,pageSize,total,data);
    }
}
