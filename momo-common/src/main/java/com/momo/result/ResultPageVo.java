package com.momo.result;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * FileName: ResultPageVo
 * Author: zipeng Li
 * Date: 2021/1/17  21:34
 * Description: 返回分页实体
 * History:
 * <author>    <time>    <version>    <desc>
 * 作者姓名     修改时间     版本号       描述
 */
@Data
@AllArgsConstructor
public class ResultPageVo<T> {
    /**
     * 返回提示信息
     */
    private String msg;
    /**
     * 返回状态码
     */
    private Integer code;
    /**
     * 当前第几页
     */
    private Integer pageNum;
    /**
     * 每页条数
     */
    private Integer pageSize;
    /**
     * 总条数
     */
    private Integer total;
    /**
     * 返回数据
     */
    private T data;

}
