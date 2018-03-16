package com.test.controller;


import com.test.model.Product;
import com.test.service.ProductMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddProductController implements Controller {
	@Autowired
	private ProductMService productMService;
	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();

		try {
			String pName = request.getParameter("pName");
			String tId = request.getParameter("tId");
			String pStyle = request.getParameter("pStyle");
			String pPrice = request.getParameter("pPrice");
			String pZt = request.getParameter("pZt");
			
			String pid = request.getParameter("pId");
			System.out.println("pid:"+ pid);
			
			if(pid != null && pid != "")
			{
				System.out.println("enter modify");
				Product product = new Product();
				product.setpName(pName);
				product.setpPrice(Float.parseFloat(pPrice));
				product.setpStyle(pStyle);
				product.setpZt(pZt);
				product.settId(Integer.parseInt(tId));
				
				product.setpId(Integer.parseInt(pid));
				
				productMService.updateProduct(product);

				List<Object> list = productMService.getProduct();

				model.put("result", list);

				return new ModelAndView("product_management", model);

			}
			else{
				System.out.println("enter add");
			Product product = new Product();
			product.setpName(pName);
			product.setpPrice(Float.parseFloat(pPrice));
			product.setpStyle(pStyle);
			product.setpZt(pZt);
			product.settId(Integer.parseInt(tId));

			productMService.addProduct(product);

			List<Object> list = productMService.getProduct();

			model.put("result", list);

			return new ModelAndView("product_management", model);
			}
		} catch (Exception e) {
			model.put("success", "失败");
			e.printStackTrace();
			return new ModelAndView("success", model);
		}
	}

}
