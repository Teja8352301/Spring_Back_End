package com.teja.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.teja.entity.Product;
import com.teja.service.UserService;

@Aspect
@Component
public class ProductAop {
	
	@Autowired
	UserService userService;
	
	@Pointcut("execution(public * com.teja.controller.ProductsController.getProducts(..))")
	public void pointCutForGetDemo() {}
	
	@Before("pointCutForGetDemo()")
	public void beforeDemo(JoinPoint joinPoint) {
		System.out.println("Executing Before Demo Method================ " + joinPoint.getSignature());
//		System.out.println(userService.getUserByIndex());
	}
	
//	@AfterReturning(pointcut="pointCutForGetDemo()",returning="response")
//	public void afterDemo(JoinPoint joinPoint,Object response) {
//		System.out.println("Exectuing After Returning Method============== ");
//	}
}
