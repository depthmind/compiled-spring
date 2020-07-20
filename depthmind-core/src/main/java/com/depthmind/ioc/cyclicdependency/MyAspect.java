package com.depthmind.ioc.cyclicdependency;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Aspect
//@Component
public class MyAspect {

    @Pointcut("execution(* com.depthmind.ioc.cyclicdependency.IndexService..*(..))")
    public void pointcut() {}

    @Before("pointcut()")
    public void aVoid() {
        System.out.println("111");
    }

    @After("pointcut()")
    public void aa() {
        System.out.println("1111222");
    }
}
