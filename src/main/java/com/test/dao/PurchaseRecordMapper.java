package com.test.dao;


import com.test.model.PurchaseRecord;

public interface PurchaseRecordMapper {
    int deleteByPrimaryKey(Integer rId);

    int insert(PurchaseRecord record);

    int insertSelective(PurchaseRecord record);

    PurchaseRecord selectByPrimaryKey(Integer rId);

    int updateByPrimaryKeySelective(PurchaseRecord record);

    int updateByPrimaryKey(PurchaseRecord record);
}