package com.depthmind.proxy.jdk;

import com.depthmind.proxy.jdk.reflect.CustomInvocationHandler;

import java.io.FileWriter;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * 用来生成代理类的工具
 * 应该在这里将生成的.java文件编译成.class文件，未在代码中体现
 * @author liuhan
 */
public class ProxyUtil {
	public static Object getInstance(Class<?> target, CustomInvocationHandler h) {
				StringBuffer stringBuffer = new StringBuffer();
				String line = "\r";
				String tab = "\t";
				stringBuffer.append("package com.depthmind.proxy.jdk;").append(line);
				stringBuffer.append("import com.depthmind.proxy.jdk.reflect.CustomInvocationHandler;").append(line);
				stringBuffer.append("public class $Proxy implements TargetInterface{").append(line);
				stringBuffer.append(tab).append("TargetInterface target;").append(line);
				stringBuffer.append(tab).append("CustomInvocationHandler h;").append(line);
				stringBuffer.append(tab).append("public $Proxy(TargetInterface target, CustomInvocationHandler h){").append(line);
				stringBuffer.append(tab).append(tab).append("this.target = target;").append(line);
				stringBuffer.append(tab).append("}").append(line);

				// 获取目标对象的方法
				Method[] declaredMethods = target.getDeclaredMethods();
				for (Method method: declaredMethods) {

					method.getModifiers();
					method.getReturnType();
					method.getName();
					Parameter[] parameters = method.getParameters();
					String params = "";
					String args = "";
					for (Parameter parameter : parameters) {
						params += parameter.getType().getSimpleName() + " " +parameter.getName() + ",";
						args += parameter.getName() + ",";
					}

					params = params.substring(0, params.lastIndexOf(",") -1);
					args = args.substring(0, args.lastIndexOf(",") -1);
					//super.h.invoke(this, m3, new Object[]{var1, var2});
					stringBuffer.append(tab).append("public " + method.getReturnType().getSimpleName() + " " + method.getName())
							.append("(").append(params).append(")  throws Exception {").append(line)
							.append(tab).append(tab).append("h.invoke(this, " + method + ", new Object[]{arg0, arg}) throws Exception ;").append(line)
			.append(tab).append(tab).append("target." + method.getName() + "(" + args + ");").append(line)
			.append(tab).append("}").append(line);

			stringBuffer.append("}");
		}

		try {
			FileWriter fileWriter = new FileWriter("/Users/liuhan/Desktop/Proxy.java");
			fileWriter.write(stringBuffer.toString());
			fileWriter.flush();
			fileWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
