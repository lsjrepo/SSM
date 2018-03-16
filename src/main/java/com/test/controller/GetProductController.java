package com.test.controller;


import com.test.service.OrderProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class GetProductController implements Controller {
	@Autowired
	private OrderProductService orderProductService;
	/**
	 * 可订购商品列表
	 */
	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		try {

			model.put("products", orderProductService.getTypeAndProduct());
			return new ModelAndView("order_product", model);
		} catch (Exception e) {
			model.put("error", "这个页面出错了");
			e.printStackTrace();
			return new ModelAndView("error", model);
		}
	}
}
