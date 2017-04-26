package com.sqe.shop.interceptor;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sqe.shop.model.User;
import com.sqe.shop.service.UserService;

@Aspect  
@Component()
public class LoginAop {

	@Autowired 
	private UserService userService;
	
	@Pointcut("execution(* com.sqe.shop.controller.*.LoginController.login(..))")  
    private void before() {} 
	
	 @Before("before()")  
    public void beforeMethod() {  
		 Subject subject = SecurityUtils.getSubject();
		 if(!subject.isAuthenticated() && subject.isRemembered()){
			String username = subject.getPrincipal().toString();
			User user = userService.findByName(username);
			UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
			token.setRememberMe(true);	
			subject.login(token);
			subject.getSession().setAttribute("userInfo", user);
		 }
    }  
}
