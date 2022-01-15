package com.teja.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.teja.entity.Product;
import com.teja.error.ErrorThrow;
import com.teja.service.UserService;

@ControllerAdvice
public class ProductAop {
	
	@Autowired
	UserService userService;
	
	@ExceptionHandler
	public ResponseEntity<Object> exceptionsHandling(ErrorThrow err){
		return new ResponseEntity(err.getMessage(),HttpStatus.ALREADY_REPORTED);
	}
	
	@Pointcut("execution(public * com.teja.controller.ProductsController.getProducts(..))")
	public void pointCutForGetDemo() {}
	
//	@Before("pointCutForGetDemo()")
//	public void beforeDemo(JoinPoint joinPoint) {
//		System.out.println("Executing Before Demo Method================ " + joinPoint.getSignature());
////		System.out.println(userService.getUserByIndex());
//	}
	
//	@AfterReturning(pointcut="pointCutForGetDemo()",returning="response")
//	public void afterDemo(JoinPoint joinPoint,Object response) {
//		System.out.println("Exectuing After Returning Method============== ");
//	}
}
