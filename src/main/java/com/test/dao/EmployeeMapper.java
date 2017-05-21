package com.test.dao;


import com.test.beans.Employee;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EmployeeMapper {

	public Employee getEmpById(Integer id);
	public List<Employee> getEmps();
}
