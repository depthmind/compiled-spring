package com.depthmind.ioc.cyclicdependency;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author liuhan
 */
@Configuration
@ComponentScan(value = "com.depthmind.ioc.cyclicdependency")
@EnableAspectJAutoProxy
public class MainConfig {
}
