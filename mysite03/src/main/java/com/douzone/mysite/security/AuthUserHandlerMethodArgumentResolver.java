package com.douzone.mysite.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.douzone.mysite.vo.UserVO;

public class AuthUserHandlerMethodArgumentResolver  implements HandlerMethodArgumentResolver{

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		
		System.out.println("AuthUserHandlerMethodArgumentResolver called");
		
		if(supportsParameter(parameter) == false) { // 지원안하는 파라미터이면
			return WebArgumentResolver.UNRESOLVED;	// 어떤결과?
		}
		
		HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
		HttpSession session = request.getSession();
		if(session == null) {
			return null;
		}
		return session.getAttribute("authUser"); // return해주는 타입이 argument로 박힘 authUser
	}
	
	
//	지원해주는 타입인가 판단함. ex) 어노테이션이 붙어있는 파라미터인지, type이 UserVO인가
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		AuthUser authUser = parameter.getParameterAnnotation(AuthUser.class);
		if(authUser == null) { // @authUser가 선언되어있지 않으면,
			return false;
		}
		
		// @authUser가 선언되어있는데 타입이 UserVO가 아니면 
		if(parameter.getParameterType().equals(UserVO.class) == false) { 
			return false;
		}
		
		return true; 
	}


}
