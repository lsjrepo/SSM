package com.test.service.impl;


import com.test.dao.ProductMapper;
import com.test.dao.ShopMapper;
import com.test.dao.StockMapper;
import com.test.model.Product;
import com.test.model.Shop;
import com.test.model.Stock;
import com.test.service.StockWarnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StockWarnServiceImpl implements StockWarnService {

	@Autowired
	private StockMapper stockMapper;
	@Autowired
	private ShopMapper shopMapper;
	@Autowired
	private ProductMapper productMapper;
	@Override
	public List<Object> stockWarn() throws Exception {
		List<Object> list = new ArrayList<Object>();
		List<Stock> stocks =stockMapper.selectAll();
		for (Stock stock : stocks) {
			Map<String, Object> stockMap = new HashMap<String, Object>();
			if (stock.getsNum() <= 10) {
				Shop shop = shopMapper.selectByPrimaryKey(stock.getShopId());
				Product product = productMapper.selectByPrimaryKey(stock.getpId());
				stockMap.put("shop", shop.getsName());
				stockMap.put("product", product.getpName());
				stockMap.put("num", stock.getsNum());

				list.add(stockMap);
			}
		}
		return list;
	}

}
