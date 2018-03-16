package com.test.controller;


import com.test.model.Employee;
import com.test.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AddStaffController implements Controller {
	@Autowired
	private StaffService staffService;
	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {

		Map<String, Object> model = new HashMap<String, Object>();

		try {

			String name = request.getParameter("name");

			String sex = request.getParameter("sex");
			if (sex != null) {
				if ("a".equals(sex)) {
					sex = "男";
				} else {
					sex = "女";
				}
			}

			String phone = request.getParameter("phone");
			String address = request.getParameter("address");

			Float price = Float.parseFloat(request.getParameter("price"));
			String note = request.getParameter("note");

			SimpleDateFormat simformat = new SimpleDateFormat("yyyy-MM-dd");
			String strbirthday = request.getParameter("birthday");
			Date birthday = simformat.parse(strbirthday);

			Employee employee = new Employee();
			employee.seteAddress(address);
			employee.seteDate(birthday);
			employee.setePhone(phone);
			employee.setePrice(price);
			employee.seteSex(sex);
			employee.seteName(name);
			if (!"".equals(note.trim())) {
				employee.seteBz(note);
			}
			UUID uuid = UUID.randomUUID();
			employee.seteId(uuid.toString());

			staffService.addStaff(employee);

			model.put("result", "success");
			model.put("success", "成功");
			return new ModelAndView("success", model);
		} catch (Exception e) {

			model.put("result", "fail");
			model.put("success", "失败");
			e.printStackTrace();
			return new ModelAndView("success", model);
		}
	}

}
