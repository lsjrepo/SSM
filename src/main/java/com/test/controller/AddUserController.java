package com.test.controller;


import com.test.model.User;
import com.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class AddUserController implements Controller {
	@Autowired
	private UserService userService;
	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		try {

			
			String uid = request.getParameter("account");
			String upwd = request.getParameter("password");
			String uname = request.getParameter("name");
			String ubz = request.getParameter("note");
			
			User user = new User();
			
			user.setuId(uid);
			user.setuPwd(upwd);
			user.setuName(uname);
			user.setuBz(ubz);
			
			userService.addUser(user);
			
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
