package com.depthmind.lazy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @author liuhan
 */
@Component
public class X {
//	@Lazy
	/**
	使用懒加载，容器中没有y这个bean时程序也不会报错。
	 不使用懒加载，require = false时也不会报错
	 不使用时程序报错，启动失败
	*/
	@Autowired(required = false)
	private Y y;
}
