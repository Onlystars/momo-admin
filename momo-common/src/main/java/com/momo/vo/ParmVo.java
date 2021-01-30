package com.momo.vo;

import lombok.Data;

/**
 * FileName: ParmVo
 * Author: zipeng Li
 * Date: 2021/1/30  16:37
 * Description: 分页参数
 * History:
 * <author>    <time>    <version>    <desc>
 * 作者姓名     修改时间     版本号       描述
 */
@Data
public class ParmVo {
    /**
     * 页容量
     */
    private int pageSize;
    /**
     * 当前页
     */
    private int currentPage;
}
