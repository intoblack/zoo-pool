package com.zero.nlp.utils;

import java.util.HashMap;
import java.util.Map;

// 登记式单例类.
// 类似Spring里面的方法，将类名注册，下次从里面直接获取。
public class Singleton {
	private static Map<String, Singleton> map = new HashMap<String, Singleton>();
	static {
		Singleton single = new Singleton();
		map.put(single.getClass().getName(), single);
	}

	// 保护的默认构造子
	protected Singleton() {
	}

	// 静态工厂方法,返还此类惟一的实例
	public static Singleton getInstance(String name) {
		if (name == null) {
			name = Singleton.class.getName();
			System.out.println("name == null" + "--->name=" + name);
		}
		if (map.get(name) == null) {
			try {
				map.put(name, (Singleton) Class.forName(name).newInstance());
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return map.get(name);
	}

	public static void main(String[] args) {
		Singleton single = Singleton.getInstance(null);
	}
}