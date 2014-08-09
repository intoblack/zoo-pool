package com.zero.nlp.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class FileUtil {

	/**
	 * 读取文件乱码，原因一：可能读的时候编码格式不对，原因二：可能打印的时候编码格式不对
	 * 这里只针对第一种情况。在读取文件的时候，判断文件编码格式，按照固定编码格式读取文件 相对来说原因二的情况比较好解决。输出时编码转换就可以了。
	 * initBookEncode 方法判断文件编码格式，主要针对简体中文
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		System.out.println(readFile("/home/lixuze/daifenci/1.txt"));
	}

	public static String readFile(String file) throws IOException {
		File f = new File(file);
		FileInputStream fis = new FileInputStream(f);
		InputStreamReader isr = new InputStreamReader(fis, initBookEncode(fis));
		BufferedReader reader = new BufferedReader(isr);
		StringBuffer sb = new StringBuffer();
		String s;
		while ((s = reader.readLine()) != null) {
			sb.append(s + "\r\n");
		}
		reader.close();
		isr.close();
		fis.close();
		return sb.toString();
	}

	/**
	 * 
	 * @param ss
	 *            待转换文本
	 * @param code
	 *            编码
	 * @return 转换后的文本
	 */
	static String changeToGBK(String ss, String code) {
		String temp = null;
		try {
			temp = new String(ss.getBytes(), code);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return temp;
	}

	/**
	 * 函数名： public static String initBookEncode(FileInputStream fileInputStream)
	 * 函数功能： 读取文件编码 函数思想： 读取文件头部信息 函数编写人： 网络
	 * 
	 * @param fileInputStream
	 * @return 文件编码
	 */
	public static String initBookEncode(FileInputStream fileInputStream) {
		String encode = "utf-8";
		try {
			byte[] head = new byte[3];
			fileInputStream.read(head);
			if (head[0] == -17 && head[1] == -69 && head[2] == -65)
				encode = "UTF-8";
			else if (head[0] == -1 && head[1] == -2)
				encode = "UTF-16";
			else if (head[0] == -2 && head[1] == -1)
				encode = "Unicode";
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return encode;
	}

	public static void walkFolder(File file, List<File> fileList) {
		if (file == null) {
			throw new IllegalArgumentException("文件路径为空　:" + file);
		}
		if (file.isFile()) {
			fileList.add(file);
		} else if (file.isDirectory()) {
			for (File childFile : file.listFiles()) {
				if (childFile.isFile()) {
					fileList.add(file);
				} else {
					walkFolder(childFile, fileList);
				}
			}
		}
	}
}
