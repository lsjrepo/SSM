package com.test.controller;


import com.test.model.Employee;
import com.test.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeleteStaffController implements Controller {
	@Autowired
	private StaffService staffService;
	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		List<Employee> elist = null;

		try {


			String id = request.getParameter("id");
			
			staffService.deleteStaff(id);

			elist = staffService.getAllStaffs();

			model.put("results", elist);
			return new ModelAndView("staff_management", model);
		} catch (Exception e) {

			model.put("result", "fail");
			model.put("success", "失败");
			e.printStackTrace();
			return new ModelAndView("success", model);
		}
	}

}
