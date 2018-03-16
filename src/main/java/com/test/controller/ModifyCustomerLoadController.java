package com.test.controller;


import com.test.model.Customer;
import com.test.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class ModifyCustomerLoadController implements Controller {
	@Autowired
	private CustomerService customerService;
	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();

		try {

			String cidString = request.getParameter("cid");
			int cid = Integer.parseInt(cidString);
			
			Customer customer = customerService.queryCustomerById(cid);

			model.put("customer", customer);

			return new ModelAndView("modify_customer", model);
		} catch (Exception e) {
			model.put("error", "操作失败");
			e.printStackTrace();
			return new ModelAndView("error", model);
		}
	}

}
