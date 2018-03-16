package com.test.controller;


import com.test.service.StockInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class StockInController implements Controller {
	@Autowired
	private StockInService stockInService;
	/**
	 * 产品入库
	 */
	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		try {

			HttpSession session = request.getSession();
			
			String uId = (String) session.getAttribute("account");
			String oid = request.getParameter("orderid");
			String timeString = request.getParameter("intime");
			String bz = request.getParameter("remark");
			String inId = "RK" + new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date(System.currentTimeMillis()));
			Date date = timeString.isEmpty() ? new Date(System.currentTimeMillis()) : Date.valueOf(timeString);
			
			int flag = stockInService.stockIn(oid, inId, date, bz, uId);
			if(flag == 1) {
				model.put("msg", "成功入库");
				return new ModelAndView("product_storage", model);
			} else {
				model.put("msg", "失败,请检查订单编号是否有效或者是否已经入库");
				return new ModelAndView("product_storage", model);
				
			}
		} catch (Exception e) {
			model.put("msg", "失败,请检查订单编号是否有效或者是否已经入库");
			e.printStackTrace();
			return new ModelAndView("product_storage", model);
		}
	}

}
