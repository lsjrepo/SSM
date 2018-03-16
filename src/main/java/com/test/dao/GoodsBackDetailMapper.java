package com.test.dao;


import com.test.model.GoodsBackDetail;

import java.util.List;

public interface GoodsBackDetailMapper {
    int deleteByPrimaryKey(Integer gId);

    /**
     * 添加退回订单的详细信息
     * @param record
     * @return
     */
    int insert(GoodsBackDetail record);

    int insertSelective(GoodsBackDetail record);

    GoodsBackDetail selectByPrimaryKey(Integer gId);

    int updateByPrimaryKeySelective(GoodsBackDetail record);

    int updateByPrimaryKey(GoodsBackDetail record);
    
    List<GoodsBackDetail> selectByBackId(int bId);
}