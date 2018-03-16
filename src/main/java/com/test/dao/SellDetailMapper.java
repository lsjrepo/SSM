package com.test.dao;



import com.test.model.SellDetail;

import java.util.List;

public interface SellDetailMapper {
    int deleteByPrimaryKey(Integer sId);

    int insert(SellDetail record);

    int insertSelective(SellDetail record);

    SellDetail selectByPrimaryKey(Integer sId);

    int updateByPrimaryKeySelective(SellDetail record);

    int updateByPrimaryKey(SellDetail record);
    
    List<SellDetail> selectBySellId(int sId);
}