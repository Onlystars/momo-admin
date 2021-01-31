package com.momo.system.department.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.momo.system.department.entity.Department;
import com.momo.system.department.mapper.DepartmentMapper;
import com.momo.system.department.service.DepartmnetService;
import org.springframework.stereotype.Service;

@Service
public class DepartmnetServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmnetService {
}
