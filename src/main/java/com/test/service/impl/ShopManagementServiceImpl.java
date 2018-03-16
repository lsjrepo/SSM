package com.test.service.impl;


import com.test.dao.ShopMapper;
import com.test.model.Shop;
import com.test.service.ShopManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ShopManagementServiceImpl implements ShopManagementService {

	@Autowired
	private ShopMapper shopMapper;
	@Override
	public List<Shop> shopShow() throws Exception {
		List<Shop> shops = shopMapper.selectAllShops();
		return shops;
	}

	@Transactional
	@Override
	public int shopAdd(Shop shop) throws Exception {
		shopMapper.insert(shop);
		return 1;
	}
	@Transactional
	@Override
	public int shopDelete(Integer sId) throws Exception {
		shopMapper.deleteByPrimaryKey(sId);
		return 1;
	}
}
