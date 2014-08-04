package com.zero.nlp.math;

/**
 * 功能： 封装数学工具类
 * 
 * @functionlist 
 * @function1 
 *            public static boolean isZero(double value) //double值比较0 
 * @function2         
 *            public static boolean isZero(float value) //float 值 比较0 
 * @function3
 *            public static boolean isZero(String value) //字符串比较 0 
 * @function4           
 *            public static booleanisZero(int value) // int值比较 0
 * 
 * @author lixuze
 * 
 */
public class MathUtils {

	public static boolean isZero(double value) {
		return (Math.abs(value) < Double.MIN_VALUE);
	}

	public static boolean isZero(float value) {
		return (Math.abs(value) < Float.MIN_VALUE);
	}

	public static boolean isZero(String value) {
		try {
			double dvalue = Double.parseDouble(value);
			return (Math.abs(dvalue) < Double.MIN_VALUE);
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;

	}

	public static boolean isZero(int value) {
		return (value == 0);
	}

	public static void main(String args[]) {
		System.out.println(isZero('\0'));
	}

}
