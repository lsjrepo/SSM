package com.test.service.impl;


import com.test.dao.CustomerMapper;
import com.test.model.Customer;
import com.test.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerMapper customerMapper;

	@Override
	public List<Customer> queryCustomer() throws Exception {
		List<Customer>  list = customerMapper.selectAll();
		return list;

	}

	@Override
	public Customer queryCustomerById(int id) throws Exception {
		Customer customer = customerMapper.selectByPrimaryKey(id);
		return customer;
	}

	@Override
	public int modifyCustomer(Customer customer) throws Exception {
		customerMapper.updateByPrimaryKey(customer);
		return 1;
	}

	@Override
	public int deleteCustomer(int id) throws Exception {
		customerMapper.deleteByPrimaryKey(id);
		return 1;
	}

	@Override
	public int addCustomer(Customer customer) throws Exception {
		customerMapper.insert(customer);
		return 1;
	}

}
