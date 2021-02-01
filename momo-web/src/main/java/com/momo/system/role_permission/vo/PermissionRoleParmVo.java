package com.momo.system.role_permission.vo;

import com.momo.system.permission.vo.TreeVo;
import lombok.Data;

import java.util.List;

/**
 * FileName: PermissionRoleParmVo
 * Author: zipeng Li
 * Date: 2021/2/1  15:47
 * Description:
 * History:
 * <author>    <time>    <version>    <desc>
 * 作者姓名     修改时间     版本号       描述
 */
@Data
public class PermissionRoleParmVo {
    private List<TreeVo> list;
    private Long roleId;
}
