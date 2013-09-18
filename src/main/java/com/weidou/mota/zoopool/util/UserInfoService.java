package com.weidou.mota.zoopool.util;

import org.hsqldb.lib.StringUtil;

import net.sf.json.JSONObject;

import com.weidou.mota.event.domain.User;
import com.weidou.mota.user.pool.UserPoolClient;

public class UserInfoService {

	/**
	 * 方法名： public static User getUserInfo(long uid)； 思想：
	 * 调用服务器thrift服务，获得用户信息，已json字符串格式返回，解析
	 * 
	 * @param uid
	 *            微博用户id
	 * @return 用户信息
	 */
	public static User getUserInfo(long uid) {
		UserPoolClient ud = new UserPoolClient();
		User user = new User();
		user = User.fromJSONObject(JSONObject.fromObject(ud.getUser(uid)));
		// 如果没有用户昵称，则无此用户
		if (StringUtil.isEmpty(user.getScreenName())) {
			return null;
		}
		return user;
	}

}
