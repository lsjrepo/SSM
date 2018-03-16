package com.test.dao;



import com.test.model.InStockDetail;

import java.util.List;

public interface InStockDetailMapper {
    int deleteByPrimaryKey(Integer iId);

    /**
     * 添加入库详情
     * @param record
     * @return
     */
    int insert(InStockDetail record);

    int insertSelective(InStockDetail record);

    InStockDetail selectByPrimaryKey(Integer iId);

    int updateByPrimaryKeySelective(InStockDetail record);

    int updateByPrimaryKey(InStockDetail record);
    
    /**
     * 根据入库id获取入库详情
     * @param inId
     * @return
     */
    List<InStockDetail> selectByInStockId(String inId);
}