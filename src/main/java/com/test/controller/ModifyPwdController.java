package com.test.controller;


import com.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class ModifyPwdController implements Controller {
	@Autowired
	private UserService userService;
	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {
		
		Map<String, Object> model = new HashMap<String, Object>();
		int ret = 0;
		
		try {

			HttpSession session = request.getSession();
			
			String uid = session.getAttribute("account").toString().trim();
			String upwd = request.getParameter("password").toString().trim();
			String newupwd = request.getParameter("newpassword").toString().trim();
			
			ret = userService.updateByPrimaryKey(uid, upwd, newupwd);
			
			if(ret == 1)
			{
				model.put("success", "操作成功");
				return new ModelAndView("success", model);
			} else{
				model.put("success", "操作失败");
				return new ModelAndView("success", model);
			}
						
		} catch (Exception e) {
			
			model.put("success", "操作失败");
			e.printStackTrace();
			return new ModelAndView("success", model);
		}
	}

}
