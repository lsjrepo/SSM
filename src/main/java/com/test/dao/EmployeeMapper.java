package com.test.dao;



import com.test.model.Employee;

import java.util.Date;
import java.util.List;

public interface EmployeeMapper {
    int deleteByPrimaryKey(String eId);

    int insert(Employee record);

    int insertSelective(Employee record);

    Employee selectByPrimaryKey(String eId);
    
    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);
    
    List<Employee> selectByDate(Date birthday);
    
    List<Employee> selectAll();
}