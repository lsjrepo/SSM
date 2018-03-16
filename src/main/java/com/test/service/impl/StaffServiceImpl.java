package com.test.service.impl;


import com.test.dao.EmployeeMapper;
import com.test.model.Employee;
import com.test.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class StaffServiceImpl implements StaffService {

	@Autowired
	private EmployeeMapper employeeMapper;
	@Transactional
	@Override
	public int addStaff(Employee employee) throws Exception {
		employeeMapper.insertSelective(employee);
		return 1;
	}

	@Override
	public List<Employee> remindBirthday(Date date) throws Exception {
		List<Employee> elist = employeeMapper.selectByDate(date);
		return elist;
	}

	@Override
	public List<Employee> getAllStaffs() throws Exception {
		List<Employee> elist = employeeMapper.selectAll();
		return elist;
	}
	@Transactional
	@Override
	public int deleteStaff(String id) throws Exception {
		employeeMapper.deleteByPrimaryKey(id);
		return 1;
	}

}
