package com.hibernet.hibert.aspect;


import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class acpecto {
   
    
    @Before("execution(* com.hibernet.hibert.web.*.*(*))")
    public void beforeAddAccountAdvice() {
		
		System.out.println("===============Ejecutando aspecto+++++=========");
		
	}
}