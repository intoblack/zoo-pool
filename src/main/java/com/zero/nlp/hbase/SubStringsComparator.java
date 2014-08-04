package com.zero.nlp.hbase;


import org.apache.hadoop.hbase.filter.SubstringComparator;
import org.apache.hadoop.hbase.util.Bytes;

/**
 * 类名： public class SubStringsComparator extends SubstringComparator
 * 
 * 类功能描述：
 *       多个字符串比较
 * 类方法：
 *      public int compareTo(byte[] value, int offset, int length) 比较函数
 *      
 * 
 * 
 * @author lixuze
 *
 */
public class SubStringsComparator extends SubstringComparator{

	private String[] subStrArry;

	public SubStringsComparator() {
		super();
	}

	/**
	 * Constructor
	 * 
	 * @param substr
	 *            the substring
	 */
	public SubStringsComparator(String substr) {
		super(substr);
		subStrArry = substr.split(" ");
	}

	@Override
	public int compareTo(byte[] value, int offset, int length) {
		int retcode = 1;
		for (String word : subStrArry) {
			if (Bytes.toString(value, offset, length).toLowerCase()
					.contains(word)) {
				retcode = 0;
				break;
			}
		}
		return retcode;
	}
	
}
