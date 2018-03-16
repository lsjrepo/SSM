package com.test.controller;


import com.test.service.ShopManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class ShopShowController implements Controller {
	@Autowired
	private ShopManagementService shopManagementService;
	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		try {

			model.put("shop", shopManagementService.shopShow());
		} catch (Exception e) {
			model.put("result", "fail");
			e.printStackTrace();
			return new ModelAndView("shop_management",model);
		}
		return new ModelAndView("shop_management",model);
	}

}
