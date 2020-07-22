package com.depthmind.factorybean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;

/**
 * @author liuhan
 */
@SuppressWarnings("rawtypes")
public class MyFactoryBean implements FactoryBean {
	@Override
	@SuppressWarnings("rawtypes")
	public Object getObject() {
		UserDao dao = (UserDao) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{UserDao.class}, new MyInvocationHandler());
		return dao;
	}

	@Override
	public Class<?> getObjectType() {
		return UserDao.class;
	}
}
