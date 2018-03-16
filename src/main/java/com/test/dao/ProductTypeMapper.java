package com.test.dao;



import com.test.model.ProductType;

import java.util.List;

public interface ProductTypeMapper {
    int deleteByPrimaryKey(Integer tId);

    int insert(ProductType record);

    int insertSelective(ProductType record);

    ProductType selectByPrimaryKey(Integer tId);

    int updateByPrimaryKeySelective(ProductType record);

    int updateByPrimaryKey(ProductType record);
    
    /**
     * 可订购商品类型
     * @return
     */
    List<ProductType> selectAllTypes();
    
    ProductType selectByTypeName(String tname);
   
}