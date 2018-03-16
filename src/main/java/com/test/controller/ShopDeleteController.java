package com.test.controller;


import com.test.service.ShopManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class ShopDeleteController implements Controller {
	@Autowired
	private ShopManagementService shopManagementService;
	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		try {
			shopManagementService.shopDelete(Integer.parseInt(request.getParameter("sId")));
			//System.out.println("........"+Integer.parseInt(request.getParameter("sId")));
			
			model.put("shop", shopManagementService.shopShow());
			model.put("result", " 成功");
			//System.out.println("...."+model.values());
		} catch (Exception e) {
			model.put("result", "失败");
			e.printStackTrace();
			return new ModelAndView("success",model);
		}
		return new ModelAndView("shop_management",model);
	}

}
