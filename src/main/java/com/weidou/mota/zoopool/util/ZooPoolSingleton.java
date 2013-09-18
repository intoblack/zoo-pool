package com.weidou.mota.zoopool.util;

/**
 * 类名 ：
 *      public class Singleton
 * 类方法：
 *      @参数：无
 *      @函数功能： 获得Singleton 公共句柄
 *      public static Singleton getInstance()；
 *      
 * @version 0.01
 * @author lixuze
 *
 */
public class ZooPoolSingleton {
	public static ZooPoolSingleton singleton = null;

	protected ZooPoolSingleton() {
		
	}

	/**
	 * 函数功能： 单例句柄
	 * 函数参数：
	 *           无
	 * 函数返回：
	 *          Singleton 公共句柄
	 * 函数思想：
	 *          所有类生成一个公共句柄
	 * @return
	 */
	public static ZooPoolSingleton getInstance() {
		if (singleton == null) {
			synchronized (ZooPoolSingleton.class) {
				if (singleton == null) {
					singleton = new ZooPoolSingleton();
				}
			}
		}
		return singleton;
	}

}