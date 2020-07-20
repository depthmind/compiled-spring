package com.depthmind.ioc.xml;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 模拟Spring容器
 * @author liuhan
 */
public class BeanFactory {

	ConcurrentHashMap<String, Object> objectMap = new ConcurrentHashMap<>(8);

	public BeanFactory(String path) {
		parseXml(path);
	}

	public Object getBean(String name) {
		return objectMap.get(name);
	}

	//@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
	@SuppressWarnings("unchecked")
	private void parseXml(String path) {
		File file = new File(this.getClass().getResource("/").getPath() + path);
		SAXReader saxReader = new SAXReader();
		try {
			Document document = saxReader.read(file);
			Element root = document.getRootElement();
			Iterator<Element> iterator = root.elementIterator();
			while (iterator.hasNext()) {
				Element next = iterator.next();
				String beanName = next.attribute("id").getValue();
				String clazzName = next.attribute("class").getValue();
				Class<?> aClass = Class.forName(clazzName);
				Object o = aClass.newInstance();
				// 拿出所有属性
				Iterator<Element> chileIterator = next.elementIterator();
				while (chileIterator.hasNext()) {
					Element element = chileIterator.next();
					Object ref = objectMap.get(element.attribute("ref").getValue());
					if (null == ref) {

					}
					String name = element.attribute("name").getValue();
					Field declaredField = aClass.getDeclaredField(name);
					declaredField.setAccessible(true);
					declaredField.set(o, ref);
				}
				objectMap.put(beanName, o);
			}
		} catch (DocumentException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
	}
}
