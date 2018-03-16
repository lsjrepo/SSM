package com.test.dao;


import com.test.model.Employee;

import java.util.List;

public interface EmployeeMapper {

	public Employee getEmpById(Integer id);
	public List<Employee> getEmps();
}
