package com.depthmind.factorybean;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author liuhan
 */
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		System.out.println("advice");
		BeanDefinitionBuilder bdb = BeanDefinitionBuilder.genericBeanDefinition(UserDao.class);
		GenericBeanDefinition bd = (GenericBeanDefinition) bdb.getBeanDefinition();
		System.out.println(bd.getBeanClassName());
		//bd.getConstructorArgumentValues().addGenericArgumentValue("com");
		bd.setBeanClass(MyFactoryBean.class);
		System.out.println(bd.getBeanClassName());
		registry.registerBeanDefinition("userDao", bd);
	}
}
