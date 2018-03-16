package com.test.controller;


import com.test.model.ProductType;
import com.test.service.ProductMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddProductTypeController implements Controller {
	@Autowired
	private ProductMService productMService;
	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();

		try {
			String typename = request.getParameter("typename");

			ProductType pt = new ProductType();
			pt.settType(typename);

			productMService.addType(pt);

			List<ProductType> list = productMService.getAllType();

			model.put("result", list);

			return new ModelAndView("class_management", model);
		} catch (Exception e) {
			model.put("success", "失败");
			e.printStackTrace();
			return new ModelAndView("success", model);
		}
	}

}
