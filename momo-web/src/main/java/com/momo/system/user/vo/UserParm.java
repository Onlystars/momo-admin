package com.momo.system.user.vo;

import com.momo.vo.ParmVo;
import lombok.Data;

/**
 * FileName: UserParm
 * Author: zipeng Li
 * Date: 2021/1/31  14:42
 * Description:
 * History:
 * <author>    <time>    <version>    <desc>
 * 作者姓名     修改时间     版本号       描述
 */
@Data
public class UserParm extends ParmVo {
    private String email;
    private String loginName;
    private String mobile;
    private int currentPage;
    private int pageSize;
    private String deptId;
}
