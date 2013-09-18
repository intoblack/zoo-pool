package com.weidou.mota.zoopool.manager;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.weidou.mota.event.domain.User;
import com.weidou.mota.redis.RedisReletead;
import com.weidou.mota.zoopool.Hbase.RelatedStatusDao;
import com.weidou.mota.zoopool.Hbase.StatusHbase;
import com.weidou.mota.zoopool.exception.ZooPoolException;
import com.weidou.mota.zoopool.moudle.ClassfierElement;
import com.weidou.mota.zoopool.util.StatusExtractUtil;
import com.weidou.mota.zoopool.util.UserInfoService;
import com.weidou.mota.zoopool.util.ZooPoolUtil;

/**
 * 类名称： ClassifierManager 类功能： 微博分类管理类，流程类 类方法： public static
 * List<ClassfierElement> preWorkByStatus(int count ) throws IOException； 从
 * statuses_2013- 随机抽取微博
 * 
 * public static List<ClassfierElement> workStatus（List<ClassfierElement>
 * result)； 从微博中获取
 * 
 * 
 * 
 * @author lixuze
 * 
 */

public class ClassifierManager {
	private static final String TABLE_NAME = "statuses_2013-07-01";

	public static List<ClassfierElement> preWorkByStatus(int count)
			throws IOException {
		return StatusHbase.scan(TABLE_NAME, count);
	}

	public static List<ClassfierElement> workStatus(
			List<ClassfierElement> result) {

		for (int i = 0; i < result.size(); i++) {
			// 防止微博出现null情况
			if (result.get(i).getStatus() == null) {
				continue;
			}
			User user = UserInfoService.getUserInfo(result.get(i).getUid());
			if (user == null) {
				result.get(i).setStatus(null);
				continue;
			}
			/*
			 * java中 boolean ？ 值1 ： 值2 ; boolean == true return 值1 else return
			 * 值2
			 */
			int isV = user.getVerifiedType() >= 0 ? 1 : 0;
			List<String> ats = StatusExtractUtil.extractAt(result.get(i)
					.getStatus());
			List<String> topics = StatusExtractUtil.extractTopic(result.get(i)
					.getStatus());
			List<String> emtions = StatusExtractUtil.extractEmtion(result
					.get(i).getStatus());
			List<String> urls = StatusExtractUtil.extractUrl(result.get(i)
					.getStatus());
			// 获得干净的微博
			List<String> clearWord = new ArrayList<String>();
			clearWord.addAll(ats);
			clearWord.addAll(topics);
			clearWord.addAll(urls);
			clearWord.addAll(emtions);
			String clearWeibo = StatusExtractUtil.getClearString(result.get(i)
					.getStatus(), clearWord);
			// 设置微博属性
			result.get(i).setStatuslen(clearWeibo.length());
			result.get(i).setUrls(urls.size());
			result.get(i).setIsV(isV);
			result.get(i).setAts(ats.size());
			result.get(i).setTopics(topics.size());
			result.get(i).setFollowers(user.getFollowers_count());
			result.get(i).setWeibocount(user.getStatuses_count());
			result.get(i).setTags(
					ZooPoolUtil.getElementString(user.getTag(), " "));
			result.get(i).setEmtions(emtions.size());
		}
		return result;
	}

	/**
	 * 函数名： public static void savefile(String path, List<ClassfierElement>
	 * result)； 函数功能： 将生成属性，保存在文件里面 函数思想： 无
	 * 
	 * @param path
	 * @param result
	 */
	public static void savefile(String path, List<ClassfierElement> result) {
		File f = new File(path);
		BufferedWriter output = null;
		try {
			output = new BufferedWriter(new FileWriter(f));
			for (ClassfierElement element : result) {
				if (element.getStatus() == null || element.getWeibocount() == 0) {
					continue;
				}
				output.write(element.toString() + "\n");
			}
		} catch (IOException e) {
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
				}
			}
		}

	}

	public static List<ClassfierElement> preWorkByReletead(int count)
			throws ZooPoolException, IOException {
		List<ClassfierElement> elementList = new ArrayList<ClassfierElement>();
		Map<Long, Long> idMap = RedisReletead.getStatusID(count);
		for (Entry<Long, Long> entry : idMap.entrySet()) {
			elementList.add(RelatedStatusDao.get(entry.getKey(),
					entry.getValue()));
		}
		return elementList;
	}

	public static void main(String args[]) throws ZooPoolException, IOException {
		savefile("/home/lixuze/classfierstatus.txt",
				workStatus(preWorkByStatus(1000)));
	}

}
