package com.zjn.exception;

/**
 * @BelongsProject: greenhand
 * @BelongsPackage: com.zjn.exception
 * @Author: ZouJiaNan
 * @CreateTime: 2020-06-18 11:56
 * @Description:
 */
public class EmployeeNotFoundException extends RuntimeException{
    public EmployeeNotFoundException(Long id){
        super("找不到" + id);
    }
}
