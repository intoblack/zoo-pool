package com.zero.nlp.math;

/**
 * 功能： 封装数学工具类
 * 
 * @functionlist
 * @function1 public static boolean isZero(double value) //double值比较0
 * @function2 public static boolean isZero(float value) //float 值 比较0
 * @function3 public static boolean isZero(String value) //字符串比较 0
 * @function4 public static booleanisZero(int value) // int值比较 0
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
		boolean zero = true;
		try {
			double dvalue = Double.parseDouble(value);
			if (!isZero(dvalue)) {
				zero = false;
			}

		} catch (Exception e) {
			zero = false;
		}
		return zero;

	}

	public static boolean isZero(int value) {
		return value == 0;
	}

	/**
	 * 
	 * 两个数字进行比较　，　选择数据最小的一个　，　如果两个值大于等于１个值为ｎｕｌｌ　，　则最小值返回ｎｕｌｌ
	 * 
	 * @param value1
	 *            　进行比较值　１　必须集成comparable 接口　，　可以进行比较的接口　，　或者为ｎｕｌｌ空
	 * @param value2
	 *            　进行比较值　２　同value1要求
	 * @return 如果两个中含有null值　，　则返回null , 如果两个值不为空　，　则返回比较小的一个
	 */
	public <T extends Comparable<? super T>> T min(T value1, T value2) {
		if (value1 == null || value2 == null) {
			return null;
		}
		if (value1.compareTo(value2) > 0) {
			return value2;
		}
		return value1;
	}

	/**
	 * 比较两个object 中最大的一个　
	 * 
	 * 
	 * @param value1
	 *            　进行比较值　１　必须集成comparable 接口　，　可以进行比较的接口　，　或者为ｎｕｌｌ空
	 * @param value2
	 *            进行比较值　２　同value1要求
	 * @return 如果value1
	 *         爲空且value2为空　，　则返回ｎｕｌｌ，　如果ｖａｌｕｅ１为空　，返回ｖａｌｕｅ２　，　如果ｖａｌｕｅ１为空　
	 *         ，返回ｖａｌｕｅ２　，　全部不为空　，　则返回最大值元素
	 */
	public <T extends Comparable<? super T>> T max(T value1, T value2) {
		if (value1 == null && value2 == null) {
			return null;
		} else if (value2 == null) {
			return value1;
		} else if (value1 == null) {
			return value2;
		}
		if (value1.compareTo(value2) >= 0) {
			return value1;
		}
		return value2;
	}

	public static void main(String args[]) {
		System.out.println(isZero('\0'));
	}

}
