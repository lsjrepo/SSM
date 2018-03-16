package com.test.controller;


import com.test.model.ProductType;
import com.test.service.ProductMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class AjaxTypeController implements Controller {
	@Autowired
	private ProductMService productMService;
	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		ProductType pt = null;
		try {
			String name = request.getParameter("typename");
			System.out.println(name);
			
			 pt = productMService.getType(name);
			
			if (pt != null ) {
				 response.getWriter().print("已经存在");
				return null;
			}
			else {
				 response.getWriter().print("是新的");
				 return null;
			}
		} catch (Exception e) {
			model.put("error", "fail");
			return null;
		}
	}

}
