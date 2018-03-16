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

public class StockOutGetProductController implements Controller {
	@Autowired
	private StockOutService stockOutService;
	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		try {

			
			List<Object> products = stockOutService.getProductByShop(1);
			List<Shop> shops = stockOutService.getAllShops();
			for (Shop shop : shops) {
				if (shop.getsId() == 1) {
					shops.remove(shop);
					break;
				}
			}
			
			model.put("shops", shops);
			model.put("products", products);
			return new ModelAndView("product_out_storage", model);
		} catch (Exception e) {
			model.put("error", "读取库存失败");
			e.printStackTrace();
			return new ModelAndView("error", model);
		}
	}

}
