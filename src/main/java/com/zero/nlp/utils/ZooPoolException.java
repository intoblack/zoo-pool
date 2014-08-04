package com.zero.nlp.utils;




/**
 * 
 * ZooPool
 * 异常类
 * 
 * #还将继续优化
 * 
 * 
 * @author lixuze
 *
 */

public class ZooPoolException extends Exception {

	private static final long serialVersionUID = 1L;

	public ZooPoolException() {
		super();
	}

	public ZooPoolException(String msg) {
		super(msg);
	}

	
	public ZooPoolException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public ZooPoolException(Throwable cause) {
		super(cause);
	}

}