package com.test.controller;


import com.test.service.StockOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;

public class StockOutController implements Controller {
	@Autowired
	private StockOutService stockOutService;
	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		try {
			

			HttpSession session = request.getSession();
			
			String uId = (String) session.getAttribute("account");
			Map<Integer, Integer> stockOut = new HashMap<Integer, Integer>();
			String dateString = request.getParameter("outtime");
			Date date = dateString.isEmpty() ? new Date(System.currentTimeMillis()) : Date.valueOf(dateString);
			String bz = request.getParameter("remark");
			
			String shopIdString = request.getParameter("shopid");
			int shopId = Integer.parseInt(shopIdString);
			
			Map<String, String[]> paraMap = request.getParameterMap();
			Iterator<String> iterator = paraMap.keySet().iterator();
			while (iterator.hasNext()) {
				String key = iterator.next();
				String value = paraMap.get(key)[0];
				Pattern pattern = Pattern.compile("[0-9]*");
				if (pattern.matcher(key).matches()) {
					if(value.isEmpty()) continue;
					stockOut.put(Integer.parseInt(key), Integer.parseInt(value));
				}
			}
			
			stockOutService.stockOut(stockOut, date, bz, uId, shopId);
			model.put("success", "产品出库成功");
			return new ModelAndView("success", model);
		} catch (Exception e) {
			model.put("error", "操作失败");
			e.printStackTrace();
			return new ModelAndView("error", model);
		}
	}

}
