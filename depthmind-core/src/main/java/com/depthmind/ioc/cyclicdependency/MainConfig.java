package com.depthmind.ioc.cyclicdependency;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(value = "com.depthmind.ioc")
@EnableAspectJAutoProxy
public class MainConfig {
}
