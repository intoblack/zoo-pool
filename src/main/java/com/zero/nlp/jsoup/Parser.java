package com.zero.nlp.jsoup;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.MalformedURLException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Parser {

	public static void parser(Document doc, Object object) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		if (doc == null || object == null) {
			throw new IllegalArgumentException();
		}

		Method methods[] = object.getClass().getDeclaredMethods();
		if (methods != null && methods.length != 0) {
			for (Method method : methods) {
				if (method.getName().startsWith("set")
						&& method.getModifiers() == Modifier.PUBLIC) { //判断是否为设置方法　，　而且为公共方法
					Class<?> parameters[] = method.getParameterTypes();
					if (parameters != null && parameters.length == 1
							&& parameters[0] == String.class) {// 判断是否有一个参数　，　并且为字符串
						JFiled jfiled = method.getAnnotation(JFiled.class);
						String value = jfiled.defaultValue();
						String path = jfiled.path();
						if (path != null && path.length() != 0) {
							value = doc.select(path).text();
						}
						method.invoke(object, value);
					}
				}
			}
		}
	}
	
	
	
	public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, MalformedURLException, IOException {
		class Test
		{
			private String title  ;
			
			
			@JFiled(path = "title" , defaultValue  = "title")
			public void setTitle(String title )
			{
				this.title = title ;
			}


			@Override
			public String toString() {
				return "Test [title=" + title + "]";
			}
			
		}
		
		
		Test t = new Test() ;
		parser(Jsoup.parse(new URL("http://www.baidu.com"), 10000), t); 
		System.out.println(t.toString());
	}
	
	
	

}
