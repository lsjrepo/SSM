package com.test.filter;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SessionFilter extends OncePerRequestFilter {

	@Override
	public void destroy() {

	}

	/**
	 * 过滤实现
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		//
		String[] undoFilter = {".do",".jsp",".js",".css",".png",".jpg"};
		String uri = request.getRequestURI();
		boolean doFilter = true;
		for(String s:undoFilter){
			
			if(uri.indexOf(s)!=-1){
				doFilter = false;
			}
			
		}
		if(uri.endsWith("JXCSystem")||uri.endsWith("JXCSystem/")){
			doFilter = false;
		}
		if(doFilter){
			Object object = request.getSession().getAttribute("account");
			System.out.println(object);
			if(null==object){
				String path = request.getContextPath();
				String basePath = request.getScheme() + "://"
						+ request.getServerName() + ":" + request.getServerPort()
						+ path + "/";
				System.out.println("Session 超时已经重定向至登陆页面");
				response.sendRedirect(basePath+"login.do");
			}else{
				 filterChain.doFilter(request, response);  
			}
		}else{
			
			filterChain.doFilter(request, response);  
		}
		
	}


}
