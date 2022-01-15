package com.teja.interceptors;

import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.ContentCachingRequestWrapper;

import com.teja.dao.UserDao;
import com.teja.entity.User;
import com.teja.service.UserService;

@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	UserDao userDao;


	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String userId = (String) request.getAttribute("activeUserId");
		if(userId.length()>0) {
			System.out.println(userId);
			User user = (User) userDao.getUser(userId);
			request.setAttribute("userAuth", user);
		}
		else {
			request.setAttribute("userAuth", null);
		}
		return super.preHandle(request, response, handler);
	}

//	@Override
//	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
//			ModelAndView modelAndView) throws Exception {
//		System.out.println("RESPONSE");
//		System.out.println(response.getOutputStream());
//		super.postHandle(request, response, handler, modelAndView);
//	}

}
