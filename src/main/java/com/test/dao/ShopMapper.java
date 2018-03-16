package com.test.dao;



import com.test.model.Shop;

import java.util.List;

public interface ShopMapper {
    int deleteByPrimaryKey(Integer sId);

    int insert(Shop record);

    int insertSelective(Shop record);

    Shop selectByPrimaryKey(Integer sId);

    int updateByPrimaryKeySelective(Shop record);

    int updateByPrimaryKey(Shop record);

	Shop selectBySName(String sName);

	List<Shop> selectAllShops();
}