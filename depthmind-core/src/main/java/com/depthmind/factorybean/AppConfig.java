package com.depthmind.factorybean;

import org.apache.ibatis.annotations.CacheNamespace;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 通过ImportBeanDefinitionRegistrar+FactoryBean模拟mybatis实例化dao
 * @author liuhan
 */
@Configuration
@Import(MyImportBeanDefinitionRegistrar.class)
@ComponentScan("com.depthmind.factorybean")
public class AppConfig {
}
