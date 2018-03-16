package com.test.controller;


import com.test.model.Shop;
import com.test.service.ShopManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class ShopAddController implements Controller {
	@Autowired
	private ShopManagementService shopManagementService;
	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		try {

			Shop shop = new Shop();
			shop.setsAddress("");
			shop.setsBz(request.getParameter("shopBz").toString());
			shop.setsName(request.getParameter("shopName").toString());
			shopManagementService.shopAdd(shop);
			
			model.put("result","success");
			model.put("success", "成功");
			model.put("shop", shopManagementService.shopShow());
		} catch (Exception e) {
			model.put("result", "fail");
			model.put("success", "失败");
			e.printStackTrace();
			return new ModelAndView("success",model);

		}
		return new ModelAndView("shop_management",model);
	}

}
