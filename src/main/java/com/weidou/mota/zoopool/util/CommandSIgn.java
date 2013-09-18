package com.weidou.mota.zoopool.util;

import java.util.ArrayList;
import java.util.List;

public class CommandSIgn {
	public final static int LQ = 0;
	public final static int RQ = 1;

	public static List<String> test(String word) {
		List<WordIndex> indexList = new ArrayList<WordIndex>();
		for (int i = 0; i < word.length(); i++) {
			if ((word.charAt(i) == '{' )&&((i + 1)< word.length())&&(word.charAt(i+1) == 'V' || word.charAt(i+1) == 'P' )) {
				indexList.add(new WordIndex(LQ, i));
			} else if (word.charAt(i) == '}') {
				indexList.add(new WordIndex(RQ, i));
			}
		}
		List<String> valueStr = new ArrayList<String>();
		for (int i = 0; i < indexList.size(); i++) {
			if (indexList.get(i).getType() == LQ) {
				int status = 1;
				for (int j = i + 1; j < indexList.size(); j++) {
					if (indexList.get(j).getType() == LQ) {
						status += 1;
					} else {
//						if(j == (indexList.size() -1) && status >0)
//						{
//							valueStr.add(word.substring(
//									indexList.get(i).getIndex(), indexList.get(j)
//											.getIndex()+1));
//							return valueStr;
//						}
						status -= 1;
					}
					if (status == 0) {
						valueStr.add(word.substring(
								indexList.get(i).getIndex(), indexList.get(j)
										.getIndex()+1));
						i = j;
						break;
					}
				}
			}
		}

		return valueStr;

	}

	public static void main(String args[]) {
		for(String str :test("{\"第\"+1+\"页\"}"))
		{
			System.out.println(str);
		}
	}

}

class WordIndex {
	private int type;
	private int index;

	public WordIndex(int type, int index) {
		this.type = type;
		this.index = index;
	}

	@Override
	public String toString() {
		return "WordIndex [type=" + type + ", index=" + index + "]";
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

}
