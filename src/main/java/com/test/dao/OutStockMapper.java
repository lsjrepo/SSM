package com.test.dao;



import com.test.model.OutStock;

import java.util.List;

public interface OutStockMapper {
    int deleteByPrimaryKey(Integer oId);

    int insert(OutStock record);

    int insertSelective(OutStock record);

    OutStock selectByPrimaryKey(Integer oId);

    int updateByPrimaryKeySelective(OutStock record);

    int updateByPrimaryKey(OutStock record);
    
    List<OutStock> selectAll();
    
    List<OutStock> selectByShopId(int sId);
}