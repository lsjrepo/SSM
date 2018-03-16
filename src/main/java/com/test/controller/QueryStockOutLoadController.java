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

public class QueryStockOutLoadController implements Controller {
	@Autowired
	private StockOutService stockOutService;
	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		try {

			List<Shop> shops = stockOutService.getAllShops();
			Shop noShop = new Shop();
			noShop.setsId(0);
			noShop.setsName("全部网点");
			shops.add(0, noShop);
			for (Shop shop : shops) {
				if (shop.getsId() == 1) {
					shops.remove(shop);
					break;
				}
			}
			
			model.put("shops", shops);
		} catch (Exception e) {
			model.put("error","fail");
			e.printStackTrace();
			return new ModelAndView("error",model);
		
		}
		
		return new ModelAndView("warehouse_out",model);
	}

}
