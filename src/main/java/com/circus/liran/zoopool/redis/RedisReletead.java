package com.circus.liran.zoopool.redis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.circus.liran.zoopool.exception.ZooPoolException;
import com.weidou.common.byteutils.LBUtiles;

import redis.clients.jedis.Jedis;

public class RedisReletead {
	private static RedisFactory redis = RedisFactory.getInstance();
	private static final int ALL_EVENT_STATUS_DB = 4;

	public static Map<byte[], byte[]> getEventRelaStatus(Long eventId)
			throws ZooPoolException {
		Jedis jedis = redis.getResource();
		jedis.select(ALL_EVENT_STATUS_DB);
		Map<byte[], byte[]> relaStat = jedis.hgetAll(LBUtiles
				.longToHighBytes(eventId));
		redis.returnResource(jedis);
		return relaStat;
	}

	public static Map<Long,Long> getStatusID(int count) throws ZooPoolException {
		Map<Long,Long> sids = new HashMap<Long, Long>();
		Jedis jedis = redis.getResource();
		jedis.select(ALL_EVENT_STATUS_DB);
		Set<byte[]> set = jedis.keys("*".getBytes());
		int ncount = 0;
		for (byte[] eid : set) {
			Map<byte[], byte[]> map = getEventRelaStatus(LBUtiles
					.highBytesToLong(eid));
			map.remove("cat".getBytes());
			for (Entry<byte[], byte[]> entry : map.entrySet()) {
				sids.put(LBUtiles.highBytesToLong(eid) ,LBUtiles.highBytesToLong(entry.getKey()));
				ncount = ncount + 1;
				break;
			}
			if (ncount >= count) {
				break;
			}
		}
		return sids;
	}

	public static void main(String args[]) throws ZooPoolException {
		Jedis jedis = redis.getResource();
		jedis.select(ALL_EVENT_STATUS_DB);
		Set<byte[]> set = jedis.keys("*".getBytes());
		for (byte[] eid : set) {
			Map<byte[], byte[]> map = getEventRelaStatus(LBUtiles
					.highBytesToLong(eid));
			map.remove("cat".getBytes());
			for (Entry<byte[], byte[]> entry : map.entrySet()) {
				System.out.println();
				System.out.println(LBUtiles.highBytesToLong(entry.getKey())
						+ "  :  " + LBUtiles.highBytesToLong(entry.getValue()));
			}

		}

	}

}
