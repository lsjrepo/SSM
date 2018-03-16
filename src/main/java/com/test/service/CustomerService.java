package com.test.service;



import com.test.model.Customer;

import java.util.List;

public interface CustomerService {
	List<Customer> queryCustomer() throws Exception;
	Customer queryCustomerById(int id) throws Exception;
	int modifyCustomer(Customer customer) throws Exception;
	int deleteCustomer(int id) throws Exception;
	int addCustomer(Customer customer) throws Exception;
}
