package com.depthmind.beandefinitionscanner;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.TypeFilter;

/**
 * @author liuhan
 */
public class MyBeanDefinitionScanner extends ClassPathBeanDefinitionScanner {
	public MyBeanDefinitionScanner(BeanDefinitionRegistry registry) {
		super(registry);
	}

	@Override
	public void addIncludeFilter(TypeFilter includeFilter) {
		super.addIncludeFilter(includeFilter);
	}
}
