package com.teja.aop;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.teja.entity.User;

@Component
@Aspect
public class UserAop {
	
	@Pointcut("execution(public Object getUserByEmail(..))")
	public void getUserByEmailAop() {}
	
	@Before("getUserByEmailAop()")
	public void beforeUserService() {
//		System.out.println("--------REQUEST----------");
	}
	

}
