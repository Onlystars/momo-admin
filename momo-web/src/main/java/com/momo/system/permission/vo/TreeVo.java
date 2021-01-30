package com.momo.system.permission.vo;

import lombok.Data;

/**
 * FileName: TreeVo
 * Author: zipeng Li
 * Date: 2021/1/28  18:04
 * Description: 权限上级树
 * History:
 * <author>    <time>    <version>    <desc>
 * 作者姓名     修改时间     版本号       描述
 */
@Data
public class TreeVo {
    //树id
    private Long id;
    //树的父id
    private Long pid;
    //树的名称
    private String name;
    //是否展开
    private Boolean open;
    //是否选中
    private Boolean checked;
}
