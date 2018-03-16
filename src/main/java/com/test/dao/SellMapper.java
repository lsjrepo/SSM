package com.test.dao;


import com.test.model.Sell;

import java.util.List;

public interface SellMapper {
    int deleteByPrimaryKey(Integer sId);

    int insert(Sell record);

    int insertSelective(Sell record);

    Sell selectByPrimaryKey(Integer sId);

    int updateByPrimaryKeySelective(Sell record);

    int updateByPrimaryKey(Sell record);
    
    List<Sell> selectAll();
    
    List<Sell> selectByShopId(int sId);
}