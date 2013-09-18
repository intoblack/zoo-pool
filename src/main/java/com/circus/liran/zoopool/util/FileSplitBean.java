package com.circus.liran.zoopool.util;

import java.util.ArrayList;
import java.util.List;

public class FileSplitBean {
	private String fileName; // 文件名称
	private int fileRow; // 文件行数
	private int randStart; // 随机数开始区域
	private int randEnd;// 文件随机数结尾区域
	private int percent; // 文件所占权重
	private List<String> fileContent = new ArrayList<String>(); //文件内容

	public int getPercent() {
		return percent;
	}

	public void setPercent(int percent) {
		this.percent = percent;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getFileRow() {
		return fileRow;
	}

	public void setFileRow(int fileRow) {
		this.fileRow = fileRow;
	}

	public int getRandStart() {
		return randStart;
	}

	public void setRandStart(int randStart) {
		this.randStart = randStart;
	}

	public int getRandEnd() {
		return randEnd;
	}

	public void setRandEnd(int randEnd) {
		this.randEnd = randEnd;
	}

	public void addFileContent(String fileLine) {
		fileContent.add(fileLine);
	}

	public List<String> getFileContent() {
		return fileContent;
	}

}
