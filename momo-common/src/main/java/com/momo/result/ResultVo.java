package com.momo.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * FileName: ResultVo
 * Author: zipeng Li
 * Date: 2021/1/17  21:33
 * Description: 返回实体数据
 * History:
 * <author>    <time>    <version>    <desc>
 * 作者姓名     修改时间     版本号       描述
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultVo<T> {
    /**
     * 返回提示信息
     */
    private String msg;
    /**
     * 返回状态码
     */
    private int code;
    /**
     * 返回数据
     */
    private T data;

}
