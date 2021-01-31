package com.momo.system.department.vo;

import com.momo.vo.ParmVo;
import lombok.Data;

@Data
public class DepartmentVo extends ParmVo {
    private String name;
    private String phone;
    private String selectPid;
}
