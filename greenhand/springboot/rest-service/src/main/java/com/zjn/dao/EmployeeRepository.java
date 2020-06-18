package com.zjn.dao;

import com.zjn.pojo.po.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @BelongsProject: greenhand
 * @BelongsPackage: com.zjn.dao
 * @Author: zjn
 * @CreateTime: 2020-06-18 11:30
 * @Description:
 */
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
