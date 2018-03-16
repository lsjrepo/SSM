package com.test.controller;


import com.test.model.Product;
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

public class GetAddProductController implements Controller {

	@Autowired
	private ProductMService productMService;
	/**
	 * 根据参数打开添加产品的页面。如果productid存在则打开修改页面，如果不存在打开添加页面
	 */
	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();

		try {


			List<ProductType> list = productMService.getAllType();

			model.put("types", list);
			
			String productid = request.getParameter("productid");
			if(productid != null && productid != "")
			{
				Product product = productMService.getProductByid(Integer.parseInt(productid));
				model.put("pid", productid);
				model.put("product", product);
				return new ModelAndView("modify_product", model);
			}	
			else
			{
			return new ModelAndView("add_product", model);
			}
		} catch (Exception e) {
			model.put("error", "操作失败");
			e.printStackTrace();
			return new ModelAndView("error", model);
		}
	}

}
