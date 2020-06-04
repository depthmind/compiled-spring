package com.depthmind.proxy.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author liuhan
 */
@Configuration
@ComponentScan("com.depthmind.proxy")
@EnableAspectJAutoProxy(proxyTargetClass = false)
public class AppConfig {
}
