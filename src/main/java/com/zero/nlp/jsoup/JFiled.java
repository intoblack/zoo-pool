package com.zero.nlp.jsoup;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;



@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface  JFiled{
	
	
	String path() default "" ;
	
	
	String defaultValue() default  ""  ;
	
	
	

}
