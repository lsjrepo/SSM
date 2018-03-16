package com.test.service;


import com.test.model.Shop;

import java.util.List;

public interface StockQueryService {
	public List<Object> stockQuery(int shopId) throws Exception;
	public List<Shop> shopQuery() throws Exception;
	public String QueryShopName(int shopId) throws Exception;
}
