package com.test.dao;

import com.test.model.Customer;

import java.util.List;

public interface CustomerMapper {
    int deleteByPrimaryKey(Integer cId);

    int insert(Customer record);

    int insertSelective(Customer record);

    Customer selectByPrimaryKey(Integer cId);

    int updateByPrimaryKeySelective(Customer record);

    int updateByPrimaryKey(Customer record);
    
    List<Customer> selectAll();
}