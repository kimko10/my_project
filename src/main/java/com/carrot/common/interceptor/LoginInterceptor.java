package com.carrot.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	// Controller 실행전 interceptor
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object obj) throws Exception {
		log.info("LoginInterceptor - preHandle");
		
		boolean result = false;
		
		if(request.getSession() != null) {
			System.out.println(request.getSession().getAttribute("test"));
			result = true;
		}
		

		return result;
	}
	
	// controller의 handler가 끝나면 처리됨
		@Override
		public void postHandle(
				HttpServletRequest request, HttpServletResponse response,
				Object obj, ModelAndView mav)
				throws Exception {
			
			//HttpSession session = request.getSession();
			log.info("LoginInterceptor - postHandle");
			//KuderUserDTO dto = kuderUserService.selectUser(obj.);
		}

}
