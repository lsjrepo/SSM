package com.test.service.impl;


import com.test.dao.ProductMapper;
import com.test.dao.ProductTypeMapper;
import com.test.model.Product;
import com.test.model.ProductType;
import com.test.service.ProductMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ProductMServiceImpl implements ProductMService {

	@Autowired
	private ProductTypeMapper productTypeMapper;
	@Autowired
	private ProductMapper productMapper;
	@Transactional
	@Override
	public int addType(ProductType productType) throws Exception{
		productTypeMapper.insertSelective(productType);
		return 1;
	}

	@Override
	public ProductType getType(String name) throws Exception {
		ProductType productType;
		productType = productTypeMapper.selectByTypeName(name);
		return productType;
	}

	@Override
	public List<ProductType> getAllType() throws Exception {
		List<ProductType> ptlist = null;
		ptlist = productTypeMapper.selectAllTypes();
		return ptlist;
	}
	@Transactional
	@Override
	public int removeType(int id) throws Exception {
		productTypeMapper.deleteByPrimaryKey(id);
		return 1;
	}

	@Override
	public List<Object> getProduct() throws Exception {
		List<Object> olist = new ArrayList<Object>();
		List<Product> plist = null;
		Iterator<Product> ite = null;
			plist = productMapper.selectAll();
		    ite = plist.iterator();
		    while(ite.hasNext())
		    {
		    	Product p = ite.next();
		    	Map<String,Object> map = new HashMap<String, Object>();
		    	map.put("pId", p.getpId());
		    	map.put("pName", p.getpName());
		    	map.put("pPrice", p.getpPrice());
		    	map.put("pStyle", p.getpStyle());
		    	map.put("pZt", p.getpZt());
		    	ProductType pt = productTypeMapper.selectByPrimaryKey(p.gettId());
		    	map.put("typename", pt.gettType());
		    	
		    	olist.add(map);
		    }
		return olist;
	}
	@Transactional
	@Override
	public int removeProduct(int pid) throws Exception {
		productMapper.deleteByPrimaryKey(pid);
		return 1;
	}
	@Transactional
	@Override
	public int addProduct(Product product) throws Exception {
		productMapper.insertSelective(product);
		return 1;
	}

	@Override
	public Product getProductByid(int id) throws Exception {
		Product product = productMapper.selectByPrimaryKey(id);
		return product;
	}
	@Transactional
	@Override
	public int updateProduct(Product product) throws Exception {
		productMapper.updateByPrimaryKey(product);
		return 1;
	}
}
