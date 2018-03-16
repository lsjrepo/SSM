package com.test.dao;


import com.test.model.OutStockDetail;

import java.util.List;

public interface OutStockDetailMapper {
    int deleteByPrimaryKey(Integer oId);

    int insert(OutStockDetail record);

    int insertSelective(OutStockDetail record);

    OutStockDetail selectByPrimaryKey(Integer oId);

    int updateByPrimaryKeySelective(OutStockDetail record);

    int updateByPrimaryKey(OutStockDetail record);
    
    List<OutStockDetail> selectByOutStocksId(int oid);
}