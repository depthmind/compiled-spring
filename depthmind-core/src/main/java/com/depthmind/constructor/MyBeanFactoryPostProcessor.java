package com.depthmind.constructor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.*;
import org.springframework.stereotype.Component;

/**
 * @author liuhan
 */
@Component
public class MyBeanFactoryPostProcessor implements BeanDefinitionRegistryPostProcessor {
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

	}

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		GenericBeanDefinition orderService = (GenericBeanDefinition) registry.getBeanDefinition("orderService");
		System.out.println("orderService装配模式" + orderService.getAutowireMode());
		orderService.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_CONSTRUCTOR);
		System.out.println("orderService装配模式" + orderService.getAutowireMode());
	}
}
