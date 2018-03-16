package com.test.controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutController implements Controller {

	/**
	 * 注销
	 */
	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		session.removeAttribute("account");
		session.removeAttribute("name");
		session.removeAttribute("password");
		return new ModelAndView("login");
	}

}
