package com.teja.interceptors;

import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.ContentCachingRequestWrapper;

import com.teja.dao.AuthDao;
import com.teja.dao.UserDao;
import com.teja.entity.AuthHeader;
import com.teja.entity.User;
import com.teja.error.ErrorThrow;
import com.teja.service.UserService;
import com.teja.utils.HttpHeadersList;
import com.teja.utils.JwtGenerator;

@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	AuthDao authDao;
	
	@Autowired
	JwtGenerator jwtG;


	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpHeadersList headersList = new HttpHeadersList();
		
		String authHeaderId = (String) request.getHeader("authid");
		System.out.println("REQUEST AUTH HEADER....."+ authHeaderId);
		String jwtToken = "";
		if(request.getHeader("jwt_token")!=null && request.getHeader("jwt_token").length()>0) {
			jwtToken = request.getHeader("jwt_token");
		}
		headersList.setHeaderMap("auth_logged", "false");
//		headersList.setHeaderMap("jwt_token", jwtToken);
		
		
		if(checkForUnAuthorisedRoute(request.getRequestURL().toString())) {
			request.setAttribute("userAuth", null);	
			if(authHeaderId != null && authHeaderId.length()>0) {
				AuthHeader authHeader = (AuthHeader) authDao.getAuthUser(authHeaderId);
				if(authHeader != null) {
					User user = authHeader.getUser();
					if(user !=null) {
						request.setAttribute("userAuth", user);	
						if(jwtToken.length()>0 && jwtG.jwtVerify(user.getId(), user.getEmail(), authHeader.getAuthId(), jwtToken)) {
							headersList.setHeaderMap("auth_logged", "true");
						}
					}
				}
		}
		}
		else if(authHeaderId != null && authHeaderId.length()>0) {
			AuthHeader authHeader = (AuthHeader) authDao.getAuthUser(authHeaderId);
			if(authHeader == null) {
				throw new ErrorThrow("user not logged");
			}
			else {
				User user = authHeader.getUser();
				if(user == null) {
					throw new ErrorThrow("user not logged");
				}
				else {
					request.setAttribute("userAuth", user);	
					if(jwtToken.length()>0 && jwtG.jwtVerify(user.getId(), user.getEmail(), authHeader.getAuthId(), jwtToken)) {
						headersList.setHeaderMap("auth_logged", "true");
					}
					else {
						headersList.setHeaderMap("auth_logged", "false");
					}
				}
			}
		}
		
		else {
			throw new ErrorThrow("user not logged");
		}
		request.setAttribute("headerMapList", headersList);
		return super.preHandle(request, response, handler);
	}
	
	public boolean checkForUnAuthorisedRoute(String requestString) {
		String[] arr = {
				"/user/",
				"/products/",
		};
		int count = 0;

		for(String i:arr) {
			if(requestString.contains(i)) {
				count++;
			}
		}
		if(count>0) {
			return true;
		}
		else {
			return false;			
		}
	}



}
