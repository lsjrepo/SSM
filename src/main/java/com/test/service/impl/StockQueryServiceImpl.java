package com.test.service.impl;


import com.test.dao.ProductMapper;
import com.test.dao.ShopMapper;
import com.test.dao.StockMapper;
import com.test.model.Product;
import com.test.model.Shop;
import com.test.model.Stock;
import com.test.service.StockQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public  class StockQueryServiceImpl implements StockQueryService {

	@Autowired
	private StockMapper stockMapper;
	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private ShopMapper shopMapper;
	public List<Object> stockQuery(int shopId) throws Exception {
		List<Object> list = new ArrayList<Object>();
		List<Stock> stocks = stockMapper.selectByshopId(shopId);
		for(Stock stock:stocks)
		{
			//System.out.print("//"+stock.getsNum());
			Product product = productMapper.selectByPrimaryKey(stock.getpId());
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("num", stock.getsNum());
			map.put("name", product.getpName());
			map.put("guige", product.getpStyle());
			map.put("price", product.getpPrice());
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Shop> shopQuery() throws Exception {
		List<Shop> list = shopMapper.selectAllShops();
		return list;
	}

	@Override
	public String QueryShopName(int shopId) throws Exception {
		Shop shop = shopMapper.selectByPrimaryKey(shopId);
		return shop.getsName();
	}

}
