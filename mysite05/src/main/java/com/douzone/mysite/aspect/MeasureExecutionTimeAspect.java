package com.douzone.mysite.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;


/* 에러컨트롤 여기서하던가 */
@Aspect
@Component
public class MeasureExecutionTimeAspect {
//	모든 리턴타입 / 패키지의 repository의 모든 클래스의 모든 메서드(파라미터는 몰라)
	@Around("execution(* *..*.repository.*.*(..)) || execution(* *..*.controller.*.*(..)) || execution(* *..*.service.*.*(..))")
	public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {

//		before
		StopWatch sw = new StopWatch();
		sw.start();
		
		Object result = pjp.proceed();
		
//		after
		sw.stop();
		Long totalTime = sw.getTotalTimeMillis(); 
				
		String className = pjp.getTarget().getClass().getName();
		String MethodName = pjp.getSignature().getName();
		String task = className + "." + MethodName;
		
		
//		System.out.println("[Execution Time]["+task+"] " + totalTime); 
		return result;
	}
}
