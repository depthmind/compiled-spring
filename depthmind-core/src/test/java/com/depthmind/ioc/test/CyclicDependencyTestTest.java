package com.depthmind.ioc.test;


import com.depthmind.ioc.cyclicdependency.IndexService;
import com.depthmind.ioc.cyclicdependency.MainConfig;
import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CyclicDependencyTestTest {

    @Test
    public void test() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        System.out.println(applicationContext.getBean(IndexService.class));
        applicationContext.getBean(IndexService.class).sout();
		IndexService bean = applicationContext.getBean(IndexService.class);
	}

}
