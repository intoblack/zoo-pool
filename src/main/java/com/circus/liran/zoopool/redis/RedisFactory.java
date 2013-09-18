package com.circus.liran.zoopool.redis;

import com.circus.liran.zoopool.exception.ZooPoolException;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisFactory {

	private static RedisFactory singleton = new RedisFactory();

	public static RedisFactory getInstance() {
		return singleton;
	}

	private JedisPool pool;

	private RedisFactory() {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxActive(5000);
		config.setMaxIdle(5000);
		config.setTestOnBorrow(true);
		pool = new JedisPool(config, "mota9", 6379, 10000);
	}

	public Jedis getResource() throws ZooPoolException {
		try {
			return pool.getResource();
		} catch (Throwable e) {
			throw new ZooPoolException(e);
		}
	}

	public void returnResource(Jedis jedis) throws ZooPoolException {
		try {
			pool.returnResource(jedis);
		} catch (Throwable e) {
			throw new ZooPoolException(e);
		}
	}
}
