package com.test.service.impl;


import com.test.dao.*;
import com.test.model.*;
import com.test.service.OrderProductService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.*;

@Service
public class OrderProductServiceImpl implements OrderProductService {

	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private ProductTypeMapper productTypeMapper;
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private OrderDetailMapper orderDetailMapper;
	@Autowired
	private UserMapper userMapper;
	/**
	 * 可订购商品列表
	 */
	@Override
	public Map<String, Object> getTypeAndProduct() throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		SqlSession sqlSession = null;
			List<ProductType> productTypes = productTypeMapper.selectAllTypes();
			for (ProductType productType : productTypes) {
				List<Product> products = productMapper.selectByType(productType.gettId());
				
				map.put(productType.gettType(), products);
			}
		return map;
	}

	@Override
	public int orderProduct(Map<Integer, Integer> orders, Date date, String bz, String uId, String oId) throws Exception{
		int ret = 0;
		Order order = new Order();
		order.setoDate(date);
		order.setoBz(bz);
		order.setoStyle(0);
		order.setuId(uId);
		order.setoId(oId);
		orderMapper.insert(order);
		Iterator<Integer> iterator = orders.keySet().iterator();
		while (iterator.hasNext()) {
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setpId(iterator.next());
			orderDetail.setoNum(orders.get(orderDetail.getpId()));
			orderDetail.setOrderId(order.getoId());
			orderDetailMapper.insert(orderDetail);
		}
		return 1;
	}

	@Override
	public List<Object> queryOrder(Date start, Date end, int style,
								   String orderId) throws Exception {
		List<Object> list = new ArrayList<Object>();
		SqlSession sqlSession = null;
		if (!orderId.isEmpty()) {
			Order order = orderMapper.selectByPrimaryKey(orderId);
			if (order != null) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("oId", order.getoId());
				map.put("date", order.getoDate());
				User user = userMapper.selectByPrimaryKey(order.getuId());
				map.put("user", user.getuName());
				List<OrderDetail> orderDetails = orderDetailMapper.selectByOrderId(order.getoId());
				int price = 0;
				for (OrderDetail orderDetail : orderDetails) {
					Product product = productMapper.selectByPrimaryKey(orderDetail.getpId());
					price += orderDetail.getoNum() * product.getpPrice();//总价
				}
				map.put("price", price);
				map.put("style", order.getoStyle());
				list.add(map);
			}

			return list;
		}
		else {
			List<Order> orders = null;
			if (style == 2) {
				orders = orderMapper.selectAll();
			}
			else {
				orders = orderMapper.selectByStyle(style);
			}

			for (Order order : orders) {
				if ((end == null ? true : order.getoDate().before(end)) && (start == null ? true : order.getoDate().after(start))) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("oId", order.getoId());
					map.put("date", order.getoDate());
					User user = userMapper.selectByPrimaryKey(order.getuId());
					map.put("user", user.getuName());
					List<OrderDetail> orderDetails = orderDetailMapper.selectByOrderId(order.getoId());
					int price = 0;
					for (OrderDetail orderDetail : orderDetails) {
						Product product = productMapper.selectByPrimaryKey(orderDetail.getpId());
						price += orderDetail.getoNum() * product.getpPrice();
					}
					map.put("price", price);
					switch (order.getoStyle()) {
						case 0:
							map.put("style", "未处理");
							break;
						case 1:
							map.put("style", "已入库");
							break;
						case -1:
							map.put("style", "已退回");
							break;
						default:
							map.put("style", "未处理");
							break;
					}

					list.add(map);
				}
			}
		}
		return list;
	}

}
