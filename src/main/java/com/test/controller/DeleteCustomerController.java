package com.test.controller;


import com.test.model.Customer;
import com.test.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeleteCustomerController implements Controller {
	@Autowired
	private CustomerService customerService;
	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();

		try {

			String cidString = request.getParameter("cid");
			int cid = Integer.parseInt(cidString);
			
			customerService.deleteCustomer(cid);
			
			List<Customer> list = customerService.queryCustomer();

			model.put("result", list);

			return new ModelAndView("customer_management", model);
		} catch (Exception e) {
			model.put("error", "操作失败");
			e.printStackTrace();
			return new ModelAndView("error", model);
		}
	}

}
