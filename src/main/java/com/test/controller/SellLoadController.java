package com.test.controller;


import com.test.model.Shop;
import com.test.service.StockOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SellLoadController implements Controller {
	@Autowired
	private StockOutService stockOutService;
	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0,
                                      HttpServletResponse arg1) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		try {

			
			Map<Integer, Object> map = new HashMap<Integer, Object>();
			List<Shop> shops = stockOutService.getAllShops();
			for (Shop shop : shops) {
				if (shop.getsId() == 1) {
					shops.remove(shop);
					break;
				}
			}
			
			for (Shop shop : shops) {
				List<Object> products = stockOutService.getProductByShop(shop.getsId());
				map.put(shop.getsId(), products);
			}
			
			model.put("shops", shops);
			model.put("shopproducts", map);
			return new ModelAndView("product_sale", model);
		} catch (Exception e) {
			model.put("error", "销售数据获取失败");
			e.printStackTrace();
			return new ModelAndView("error", model);
		}
	}

}
