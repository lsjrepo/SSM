package com.test.service.impl;


import com.test.dao.*;
import com.test.model.*;
import com.test.service.DetailQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DetailQueryServiceImpl implements DetailQueryService {

	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private OrderDetailMapper orderDetailMapper;
	@Autowired
	private InStockDetailMapper inStockDetailMapper;
	@Autowired
	private GoodsBackDetailMapper goodsBackDetailMapper;
	@Autowired
	private OutStockDetailMapper outStockDetailMapper;
	@Autowired
	private SellDetailMapper sellDetailMapper;
	@Override
	public List<Object> queryDetail(String dId, String table) throws Exception {
		List<Object> list = new ArrayList<Object>();

			if (table.equals("order")) {
				List<OrderDetail> orderDetails = orderDetailMapper.selectByOrderId(dId);
				for (OrderDetail orderDetail : orderDetails) {
					Map<String, Object> map = new HashMap<String, Object>();
					Product product = productMapper.selectByPrimaryKey(orderDetail.getpId());
					map.put("name", product.getpName());
					map.put("num", orderDetail.getoNum());
					map.put("price", product.getpPrice() * orderDetail.getoNum());
					list.add(map);
				}
			}
			else if (table.equals("in")) {
				List<InStockDetail> inStockDetails = inStockDetailMapper.selectByInStockId(dId);
				for (InStockDetail inStockDetail : inStockDetails) {
					Map<String, Object> map = new HashMap<String, Object>();
					Product product = productMapper.selectByPrimaryKey(inStockDetail.getpId());
					map.put("name", product.getpName());
					map.put("num", inStockDetail.getiNum());
					map.put("price", product.getpPrice() * inStockDetail.getiNum());
					list.add(map);
				}
			}
			else if (table.equals("back")) {
				int id = Integer.parseInt(dId);
				List<GoodsBackDetail> goodsBackDetails = goodsBackDetailMapper.selectByBackId(id);
				for (GoodsBackDetail goodsBackDetail : goodsBackDetails) {
					Map<String, Object> map = new HashMap<String, Object>();
					Product product = productMapper.selectByPrimaryKey(goodsBackDetail.getpId());
					map.put("name", product.getpName());
					map.put("num", goodsBackDetail.getgNum());
					map.put("price", product.getpPrice() * goodsBackDetail.getgNum());
					list.add(map);
				}
			}
			else if (table.equals("sell")) {
				int id = Integer.parseInt(dId);
				List<SellDetail> sellDetails = sellDetailMapper.selectBySellId(id);
				for (SellDetail sellDetail : sellDetails) {
					Map<String, Object> map = new HashMap<String, Object>();
					Product product = productMapper.selectByPrimaryKey(sellDetail.getpId());
					map.put("name", product.getpName());
					map.put("num", sellDetail.getsNum());
					map.put("price", product.getpPrice() * sellDetail.getsNum());
					list.add(map);
				}
			}
			else if (table.equals("out")) {
				int id = Integer.parseInt(dId);
				List<OutStockDetail> outStockDetails = outStockDetailMapper.selectByOutStocksId(id);
				
				for (OutStockDetail outStockDetail : outStockDetails) {
					Map<String, Object> map = new HashMap<String, Object>();
					Product product = productMapper.selectByPrimaryKey(outStockDetail.getpId());
					
					map.put("name", product.getpName());
					map.put("num", outStockDetail.getoNum());
					map.put("price", product.getpPrice() * outStockDetail.getoNum());
					
					list.add(map);
				}
			}
		return list;
	}

}
