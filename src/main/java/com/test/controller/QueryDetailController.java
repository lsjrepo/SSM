package com.test.controller;


import com.test.service.DetailQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class QueryDetailController implements Controller {
	@Autowired
	private DetailQueryService detailQueryService;
	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		try {

			String did = request.getParameter("did");
			String table = request.getParameter("table");
			model.put("details", detailQueryService.queryDetail(did, table));
			model.put("did", did);
			return new ModelAndView("detail", model);
		} catch (Exception e) {
			model.put("error", "获取失败");
			e.printStackTrace();
			return new ModelAndView("error", model);
		}
	}

}
