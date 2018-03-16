package com.test.service;



import com.test.model.Shop;

import java.util.List;

public interface ShopManagementService {
	public List<Shop> shopShow() throws Exception;
	public int shopAdd(Shop shop) throws Exception;
	int shopDelete(Integer sId) throws Exception; 
			
}
