package com.depthmind.ioc.cyclicdependency;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author liuhan
 */
@Aspect
@Component
public class MyAspect1 {

    @Pointcut("execution(* com.depthmind.ioc.cyclicdependency.IndexService..*(..))")
    public void pointcut() {}

    @Before("pointcut()")
    public void a1() {
        System.out.println("a111");
    }

    @After("pointcut()")
    public void a2() {
        System.out.println("a1111222");
    }
}
