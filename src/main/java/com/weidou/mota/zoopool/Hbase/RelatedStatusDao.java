package com.weidou.mota.zoopool.Hbase;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.hsqldb.lib.StringUtil;

import com.weidou.mota.zoopool.moudle.ClassfierElement;

public class RelatedStatusDao {
	public static final String TABLE_NAME = "relatedStatus";
	private static final byte[] family = Bytes.toBytes("a");
	private static final byte[] type = Bytes.toBytes("rs");
	private static final byte[] text = Bytes.toBytes("text");
	private static final byte[] uid = Bytes.toBytes("uid");
	private static Configuration conf = null;

	static {
		conf = HBaseConfiguration.create();
	}

	public static ClassfierElement get(long eid , long id) throws IOException {
		
		ClassfierElement element = new ClassfierElement();
		HTable table = new HTable(conf, TABLE_NAME);
		Get get = new Get(eidSid2relRowkey(eid,id));
		String textStatus = "";
		long userid = 0;

		Result result = table.get(get);
		byte[] rsbytes = result.getValue(family, type);
		if (rsbytes == null || rsbytes.length == 0
				|| Bytes.toLong(rsbytes) == 0) {
			if (result.containsColumn(family, text)) {
				textStatus = Bytes.toString(result.getValue(family, text));
			}
			if (result.containsColumn(family, uid)) {
				userid = Bytes.toLong(result.getValue(family, uid));
			}
		}
		if (!StringUtil.isEmpty(textStatus) && userid != 0) {
			element.setStatus(textStatus);
			element.setUid(userid);
		}
		return element;
	}
	
	private static byte[] eidSid2relRowkey(long eid, long sid) {
		return Bytes.toBytes(eid + "_" + sid);
	}
}
