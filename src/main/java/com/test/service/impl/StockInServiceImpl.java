package com.test.service.impl;


import com.test.dao.*;
import com.test.model.*;
import com.test.service.StockInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StockInServiceImpl implements StockInService {

	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private OrderDetailMapper orderDetailMapper;
	@Autowired
	private InStockMapper inStockMapper;
	@Autowired
	private InStockDetailMapper inStockDetailMapper;
	@Autowired
	private StockMapper stockMapper;
	@Autowired
	private GoodsBackMapper goodsBackMapper;
	@Autowired
	private GoodsBackDetailMapper goodsBackDetailMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private ProductMapper productMapper;
	@Transactional
	@Override
	public int stockIn(String orderId, String inId, Date date, String bz, String uId) throws Exception{
			//此订单应该未处理
			if (orderMapper.selectByPrimaryKey(orderId).getoStyle() != 0) {
				throw new Exception();
			}
			InStock inStock = new InStock();
			inStock.setuId(uId);
			inStock.setoId(orderId);
			inStock.setiId(inId);
			inStock.setiDate(date);
			inStockMapper.insert(inStock);
			Order order = new Order();
			order.setoBz(null);
			order.setoDate(null);
			order.setoId(orderId);
			order.setoStyle(1);
			order.setuId(null);
			
			orderMapper.updateByPrimaryKeySelective(order);
			
			List<OrderDetail> orderDetails = orderDetailMapper.selectByOrderId(orderId);
			List<Stock> stocks = stockMapper.selectAll();
			
			for (OrderDetail orderDetail : orderDetails) {
				InStockDetail inStockDetail = new InStockDetail();
				inStockDetail.setInstockId(inId);
				inStockDetail.setiNum(orderDetail.getoNum());
				inStockDetail.setpId(orderDetail.getpId());
				inStockDetailMapper.insert(inStockDetail);
				
				boolean flag = false;
				for (Stock stock : stocks) {
					if (stock.getShopId() == 1 && stock.getpId() == orderDetail.getpId()) {
						stock.setsNum(stock.getsNum() + orderDetail.getoNum());
						stockMapper.updateByPrimaryKey(stock);
						flag = true;
						break;
					}
				}
				if (!flag) {
					Stock stock = new Stock();
					stock.setShopId(1);
					stock.setpId(orderDetail.getpId());
					stock.setsMaxnum(Integer.MAX_VALUE);
					stock.setsMinnum(0);
					stock.setsNum(orderDetail.getoNum());
					stockMapper.insert(stock);
				}
			}
		return 1;
	}
	@Transactional
	@Override
	public int goodsBack(String orderId, Date date, String bz,
			String uId) throws Exception {

			if (orderMapper.selectByPrimaryKey(orderId).getoStyle() != 0) {
				throw new Exception();
			}
			GoodsBack goodsBack = new GoodsBack();
			
			goodsBack.setuId(uId);
			goodsBack.setgDate(date);
			goodsBack.setgBz(bz);
			
			goodsBackMapper.insert(goodsBack);
			
			Order order = new Order();
			order.setoBz(null);
			order.setoDate(null);
			order.setoId(orderId);
			order.setoStyle(-1);
			order.setuId(null);
			
			orderMapper.updateByPrimaryKeySelective(order);
			
			List<OrderDetail> orderDetails = orderDetailMapper.selectByOrderId(orderId);
			
			for (OrderDetail orderDetail : orderDetails) {
				GoodsBackDetail goodsBackDetail = new GoodsBackDetail();
				goodsBackDetail.setGoodsbackId(goodsBack.getgId());
				goodsBackDetail.setgNum(orderDetail.getoNum());
				goodsBackDetail.setpId(orderDetail.getpId());
				goodsBackDetailMapper.insert(goodsBackDetail);
			}
		return 1;
	}

	@Override
	public List<Object> queryStockIn(Date start, Date end, String orderId)
			throws Exception {
		   List<Object> list = new ArrayList<Object>();
			if (!orderId.isEmpty()) {
				InStock inStock = inStockMapper.selectByPrimaryKey(orderId);
				if (inStock != null) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("oId", inStock.getiId());
					map.put("date", inStock.getiDate());
					User user = userMapper.selectByPrimaryKey(inStock.getuId());
					map.put("user", user.getuName());
					List<InStockDetail> inStockDetails = inStockDetailMapper.selectByInStockId(inStock.getiId());
					int price = 0;
					for (InStockDetail inStockDetail : inStockDetails) {
						Product product = productMapper.selectByPrimaryKey(inStockDetail.getpId());
						price += inStockDetail.getiNum() * product.getpPrice();
					}
					map.put("price", price);
					list.add(map);
				}
				return list;
			}
			else {
				List<InStock> inStocks = inStockMapper.selectAll();
				
				for (InStock inStock : inStocks) {
					if ((end == null ? true : inStock.getiDate().before(end)) && (start == null ? true : inStock.getiDate().after(start))) {
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("oId", inStock.getiId());
						map.put("date", inStock.getiDate());
						User user = userMapper.selectByPrimaryKey(inStock.getuId());
						map.put("user", user.getuName());
						List<InStockDetail> inStockDetails = inStockDetailMapper.selectByInStockId(inStock.getiId());
						int price = 0;
						for (InStockDetail inStockDetail : inStockDetails) {
							Product product = productMapper.selectByPrimaryKey(inStockDetail.getpId());
							price += inStockDetail.getiNum() * product.getpPrice();
						}
						map.put("price", price);
						
						list.add(map);
					}
				}
			}
		return list;
	}

	@Override
	public List<Object> queryReturn(Date start, Date end, int orderId)
			throws Exception {
		List<Object> list = new ArrayList<Object>();
			if (orderId != -1) {
				GoodsBack goodsBack = goodsBackMapper.selectByPrimaryKey(orderId);
				
				if (goodsBack != null) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("oId", goodsBack.getgId());
					map.put("date", goodsBack.getgDate());
					User user = userMapper.selectByPrimaryKey(goodsBack.getuId());
					map.put("user", user.getuName());
					List<GoodsBackDetail> goodsBackDetails = goodsBackDetailMapper.selectByBackId(goodsBack.getgId());
					int price = 0;
					for (GoodsBackDetail goodsBackDetail : goodsBackDetails) {
						Product product = productMapper.selectByPrimaryKey(goodsBackDetail.getpId());
						price += goodsBackDetail.getgNum() * product.getpPrice();
					}
					map.put("price", price);
					list.add(map);
				}
				
				return list;
			}
			else {
				List<GoodsBack> goodsBacks = goodsBackMapper.selectAll();
				for (GoodsBack goodsBack : goodsBacks) {
					if ((end == null ? true : goodsBack.getgDate().before(end)) && (start == null ? true : goodsBack.getgDate().after(start))) {
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("oId", goodsBack.getgId());
						map.put("date", goodsBack.getgDate());
						User user = userMapper.selectByPrimaryKey(goodsBack.getuId());
						map.put("user", user.getuName());
						List<GoodsBackDetail> goodsBackDetails = goodsBackDetailMapper.selectByBackId(goodsBack.getgId());
						int price = 0;
						for (GoodsBackDetail goodsBackDetail : goodsBackDetails) {
							Product product = productMapper.selectByPrimaryKey(goodsBackDetail.getpId());
							price += goodsBackDetail.getgNum() * product.getpPrice();
						}
						map.put("price", price);
						
						list.add(map);
					}
				}
			}
		return list;
	}

}
