package com.depthmind.factorybean;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author liuhan
 */
@Configuration
@Import(MyImportBeanDefinitionRegistrar.class)
@ComponentScan("com.depthmind.factorybean")
public class AppConfig {
}
