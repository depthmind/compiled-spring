package com.depthmind.ioc.applicationcontext;

import com.depthmind.ioc.postprocessor.MyBeanPostProcessor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class MyApplicationContext extends AbstractApplicationContext {
    @Override
    protected void refreshBeanFactory() throws BeansException, IllegalStateException {

    }

    @Override
    protected void closeBeanFactory() {

    }

    @Override
    public ConfigurableListableBeanFactory getBeanFactory() throws IllegalStateException {
        return null;
    }

    @Override
    protected void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
        beanFactory.addBeanPostProcessor(new MyBeanPostProcessor());
    }
}
